  import { Component, OnInit } from '@angular/core';
  import { CommonModule } from '@angular/common';
  import { HttpClient } from '@angular/common/http';
  import { firstValueFrom } from 'rxjs';
  import { Router } from '@angular/router';
  import { FormsModule } from '@angular/forms';

  interface Product {
    id: number;
    name: string;
    price: number;
    image: string;
  }

  interface OrderItem {
    productId: number;
    quantity: number;
    price: number;
    name?: string;
  }

  interface Order {
    id: number;
    accountId: number;
    createdAt: string;
    items: OrderItem[];
    expanded?: boolean;
    rated?: boolean;
    showRatingModal?: boolean;
  }

  @Component({
    selector: 'app-orders',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './orders.component.html',
    styleUrls: ['./orders.component.css']
  })
  export class OrdersComponent implements OnInit {
    orders: Order[] = [];
    products: Product[] = [];
    currentRating: number = 0;
    temporaryRating: number = 0;
    ratingComment: string = '';

    constructor(
      private http: HttpClient,
      private router: Router
    ) {}

    goBack() {
      this.router.navigate(['/catalog']);
    }

    ngOnInit() {
      const accountId = localStorage.getItem('accountId');
      if (accountId) {
        this.loadOrdersWithProducts(parseInt(accountId));
      }
    }

    async loadOrdersWithProducts(accountId: number) {
      try {
        const storeId = localStorage.getItem('selectedStoreId');
        this.products = await firstValueFrom(
          this.http.get<Product[]>(`http://localhost:8082/product?storeId=${storeId}`)
        );

        const orders = await firstValueFrom(
          this.http.get<Order[]>(`http://localhost:8083/order?accountId=${accountId}`)
        );

        this.orders = orders.map(order => ({
          ...order,
          expanded: false,
          items: order.items.map(item => {
            const product = this.products.find(p => p.id === item.productId);
            return {
              ...item,
              name: product?.name || `Producto ${item.productId}` 
            };
          })
        }));

        // Verificar calificaciones existentes para cada pedido
        for (let order of this.orders) {
          try {
            const hasRating = await firstValueFrom(
              this.http.get<boolean>(
                `http://localhost:8084/rating/check?orderId=${order.id}&storeId=${localStorage.getItem('selectedStoreId')}`
              )
            );
            order.rated = hasRating;
          } catch (error) {
            console.error(`Error checking rating for order ${order.id}:`, error);
            order.rated = false;
          }
        }

      } catch (error) {
        console.error('Error loading orders with products:', error);
      }
  }

    getOrderTotal(order: Order): number {
      return order.items.reduce((total, item) => total + (item.price * item.quantity), 0);
    }

    toggleDetails(order: Order) {
      order.expanded = !order.expanded;
    }

    openRatingModal(order: Order) {
      order.showRatingModal = true;
      this.currentRating = 0;
      this.ratingComment = '';
    }

    closeRatingModal(order: Order) {
      order.showRatingModal = false;
      this.currentRating = 0;
      this.temporaryRating = 0;
      this.ratingComment = '';
    }

    onStarHover(rating: number) {
      this.temporaryRating = rating;
    }

    onStarLeave() {
      this.temporaryRating = 0;
    }

    selectRating(rating: number) {
      this.currentRating = rating;
    }

    async submitRating(order: Order) {
      if (this.currentRating === 0) {
        alert('Por favor selecciona una calificación');
        return;
      }

      try {
        const ratingData = {
          orderId: order.id,
          storeId: localStorage.getItem('selectedStoreId'),
          rating: this.currentRating,
          comment: this.ratingComment
        };

        await firstValueFrom(this.http.post('http://localhost:8084/rating', ratingData));
        
        order.rated = true;  // Marcar el pedido como calificado
        order.showRatingModal = false;
        this.currentRating = 0;
        this.temporaryRating = 0;
        this.ratingComment = '';
        
        alert('¡Gracias por tu calificación!');
      } catch (error) {
        console.error('Error al enviar la calificación:', error);
        alert('Hubo un error al enviar tu calificación. Por favor intenta de nuevo.');
      }
    }
  }