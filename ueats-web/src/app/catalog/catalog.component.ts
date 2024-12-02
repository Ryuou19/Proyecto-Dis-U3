import { Component, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-catalog',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {
  userCity: string = '';
  stores: any[] = [];

  constructor(private http: HttpClient) {}

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
}
