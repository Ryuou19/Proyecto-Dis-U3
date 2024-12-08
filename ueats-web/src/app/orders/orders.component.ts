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
    storeId: number;
  }

  interface OrderItem {
    productId: number;
    quantity: number;
    price: number;
    name?: string;
    storeId?: number;
  }

  interface Order {
    id: number;
    accountId: number;
    storeId: number;
    createdAt: string;
    items: OrderItem[];
    expanded?: boolean;
    rated?: boolean;
    showRatingModal?: boolean;
    storeName?: string;
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
        const userCity = localStorage.getItem('userCity');
        if (!userCity) {
          throw new Error('Ciudad del usuario no encontrada');
        }
    
        const stores = await firstValueFrom(
          this.http.get<any[]>(`http://localhost:8082/store?city=${userCity}`)
        );
    
        const orders = await firstValueFrom(
          this.http.get<Order[]>(`http://localhost:8083/order?accountId=${accountId}`)
        );
    
        const productsPromises = stores.map(store => 
          firstValueFrom(this.http.get<Product[]>(`http://localhost:8082/product?storeId=${store.id}`))
        );
        const productsArrays = await Promise.all(productsPromises);
        this.products = productsArrays.flat();
    
        const ratingResults = await Promise.all(orders.map(async (order) => {
          try {
            for (const store of stores) {
              const hasRating = await firstValueFrom(
                this.http.get<boolean>(`http://localhost:8084/rating/check?orderId=${order.id}&storeId=${store.id}`)
              );
              if (hasRating) {
                return { orderId: order.id, rated: true };
              }
            }
            return { orderId: order.id, rated: false };
          } catch (error) {
            return { orderId: order.id, rated: false };
          }
        }));
    
        this.orders = orders.map(order => {
          const ratingResult = ratingResults.find(r => r.orderId === order.id);
          
          return {
            ...order,
            expanded: false,
            rated: ratingResult?.rated || false,
            items: order.items.map(item => {
              const product = this.products.find(p => p.id === item.productId);
              return {
                ...item,
                name: product?.name || `Producto ${item.productId}`
              };
            })
          };
        });
    
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
        // Obtener el primer item del pedido
        const firstOrderItem = order.items[0];
        const productId = firstOrderItem.productId;
    
        // Obtener la tienda correcta buscando el producto entre todas las tiendas
        const userCity = localStorage.getItem('userCity');
        if (!userCity) {
          throw new Error('Ciudad del usuario no encontrada');
        }
    
        // Obtener todas las tiendas de la ciudad
        const stores = await firstValueFrom(
          this.http.get<any[]>(`http://localhost:8082/store?city=${userCity}`)
        );
    
        // Buscar en qué tienda está el producto
        let correctStoreId: number | null = null;
        for (const store of stores) {
          const products = await firstValueFrom(
            this.http.get<Product[]>(`http://localhost:8082/product?storeId=${store.id}`)
          );
          
          const foundProduct = products.find(p => p.id === productId);
          if (foundProduct) {
            correctStoreId = store.id;
            break;
          }
        }
    
        if (correctStoreId === null) {
          throw new Error('No se pudo encontrar la tienda del producto');
        }
    
        const ratingData = {
          orderId: order.id,
          storeId: correctStoreId,
          rating: this.currentRating,
          comment: this.ratingComment || ""
        };
    
        console.log('Payload a enviar:', ratingData);
    
        await firstValueFrom(
          this.http.post('http://localhost:8084/rating', ratingData)
        );
        
        order.rated = true;
        order.showRatingModal = false;
        this.currentRating = 0;
        this.temporaryRating = 0;
        this.ratingComment = '';
        
        alert('¡Gracias por tu calificación!');
      } catch (error) {
        console.error('Error completo:', error);
        if (error instanceof Error) {
          console.error('Mensaje de error:', error.message);
        }
        alert('Hubo un error al enviar tu calificación. Por favor intenta de nuevo.');
      }
    }
  }
