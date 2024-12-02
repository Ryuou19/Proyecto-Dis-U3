import { Component, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-catalog',
  standalone: true,
  imports: [HttpClientModule, CommonModule], // Usa HttpClientModule aquí
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {
  userCity: string = '';
  stores: any[] = [];

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.userCity = localStorage.getItem('userCity') || '';
    console.log('Loaded City from LocalStorage:', this.userCity);

    if (this.userCity) {
      this.getStoresByCity(this.userCity);
    } else {
      console.warn('No se encontró la ciudad del usuario en localStorage');
      this.stores = [];
    }
  }

  getStoresByCity(city: string): void {
    console.log(`Requesting stores for city: ${city}`);
    this.http.get<any[]>(`http://localhost:8082/store?city=${city}`).subscribe(
      response => {
        console.log('Stores received:', response);
        this.stores = response;
      },
      error => {
        console.error('Error al obtener las tiendas:', error);
        this.stores = [];
      }
    );
  }

  // Guardar el ID de la tienda seleccionada y redirigir
  saveSelectedStoreId(storeId: number): void {
    localStorage.setItem('selectedStoreId', storeId.toString());
    alert(`Store ID ${storeId} saved to localStorage`);
    this.router.navigate(['/products']); // Navegar a la ruta "/products"
  }
}
