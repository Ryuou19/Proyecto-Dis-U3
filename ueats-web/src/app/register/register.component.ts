import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  email: string = '';
  password: string = '';
  name: string = '';
  phone: string = '';
  street: string = '';
  city: string = '';

  // URL base dinámica
  apiUrl: string;

  constructor(private router: Router, private http: HttpClient) {
    // Determinar la URL según el entorno
    this.apiUrl = /Android/i.test(navigator.userAgent)
      ? 'http://10.0.2.2:8081' // Para el emulador Android
      : 'http://localhost:8081'; // Para la página web
  }

  onRegister() {
    this.http.post<any>(`${this.apiUrl}/account/register`, {
      email: this.email,
      password: this.password,
      name: this.name,
      phone: this.phone,
      street: this.street,
      city: this.city
    }).subscribe(
      response => {
        this.router.navigate(['/']);
      },
      error => {
        alert('Registration failed');
      }
    );
  }
}
