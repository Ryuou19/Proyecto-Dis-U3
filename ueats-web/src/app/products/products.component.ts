import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule], // Importación necesaria para directivas estructurales como *ngIf
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  storeId: number = 0;
  products: any[] = [];
  cartOpen: boolean = false; // Controla si el carrito está abierto

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    // Obtener storeId desde localStorage
    const storedStoreId = localStorage.getItem('selectedStoreId');
    if (storedStoreId) {
      this.storeId = parseInt(storedStoreId, 10);
      console.log('Store ID loaded from localStorage:', this.storeId);
      this.getProductsByStore(this.storeId);
    } else {
      console.error('No store ID found in localStorage.');
    }
  }

  getProductsByStore(storeId: number): void {
    this.http.get<any[]>(`http://localhost:8082/product?storeId=${storeId}`).subscribe(
      response => {
        console.log('Productos recibidos:', response);
        this.products = response;
      },
      error => {
        console.error('Error al obtener los productos:', error);
      }
    );
  }

  // Abre o cierra el carrito
  toggleCart(): void {
    this.cartOpen = !this.cartOpen;
  }

  // Cierra el carrito
  closeCart(): void {
    this.cartOpen = false;
  }

  // Limpia los productos del carrito
  clearCart(): void {
    alert('Carrito limpiado.');
    // Aquí puedes implementar la lógica para limpiar el carrito
  }

  // Realiza el pedido
  placeOrder(): void {
    alert('Pedido realizado.');
    // Aquí puedes implementar la lógica para realizar el pedido
  }
}
