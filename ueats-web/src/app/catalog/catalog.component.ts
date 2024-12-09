import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';

interface Store {
  id: number;
  name: string;
  category: string;
  image: string;
  rating: number;
  city: string;
}

interface Rating {
  rating: number;
  comment: string;
  createdAt: string;
}

interface RatingResponse {
  averageRating: number;
  ratings: Rating[];
}

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css'],
  standalone: true,
  imports: [CommonModule, DatePipe],
})
export class CatalogComponent implements OnInit {
  stores: Store[] = [];
  userCity: string = '';
  selectedStoreReviews: any = null;

  // URL base dinámica
  apiUrl: string;

  constructor(private http: HttpClient, private router: Router) {
    // Determinar la URL según el entorno
    this.apiUrl = /Android/i.test(navigator.userAgent)
      ? 'http://10.0.2.2' // Para el emulador Android
      : 'http://localhost'; // Para la página web
  }

  ngOnInit() {
    this.userCity = localStorage.getItem('userCity') || '';
    this.loadStores();
  }

  loadStores() {
    this.http.get<Store[]>(`${this.apiUrl}:8082/store?city=${this.userCity}`).subscribe(
      stores => {
        // Para cada tienda, obtener su calificación promedio
        stores.forEach(store => {
          this.loadStoreRating(store);
        });
        this.stores = stores;
      },
      error => console.error('Error loading stores:', error)
    );
  }

  loadStoreRating(store: Store) {
    this.http.get<RatingResponse>(`${this.apiUrl}:8084/rating?storeId=${store.id}`).subscribe(
      response => {
        store.rating = response.averageRating;
      },
      error => {
        console.error('Error loading store rating:', error);
        store.rating = 0;
      }
    );
  }

  showReviews(store: Store) {
    this.http.get<RatingResponse>(`${this.apiUrl}:8084/rating?storeId=${store.id}`).subscribe(
      response => {
        this.selectedStoreReviews = {
          ...response,
          storeName: store.name
        };
      },
      error => {
        console.error('Error loading reviews:', error);
        alert('Error al cargar las reseñas');
      }
    );
  }

  closeReviews() {
    this.selectedStoreReviews = null;
  }

  selectStore(store: Store) {
    localStorage.setItem('selectedStoreId', store.id.toString());
    this.router.navigate(['/products']);
  }
}
