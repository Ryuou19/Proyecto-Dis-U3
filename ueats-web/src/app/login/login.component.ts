import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private router: Router, private http: HttpClient) {}

  onLogin() {
    this.http.post<any>('http://localhost:8081/account/login', { email: this.email, password: this.password }).subscribe(
      response => {
        console.log(response); 
        if (response.accountId) {
          localStorage.setItem('accountId', response.accountId.toString()); // Guarda solo accountId
          

          this.http.get<any>(`http://localhost:8081/profile?accountId=${response.accountId}`).subscribe(
            profileResponse => {
              console.log(profileResponse); 
              localStorage.setItem('userCity', profileResponse.city); 
              // Aqui se guardar más información si es necesario para identificar cosas recibidas
              this.router.navigate(['/catalog']); // envia al catalogo
            },
            profileError => {
              console.error('Error fetching profile:', profileError);
              alert('Failed to fetch user profile');
            }
          );
        } else {
          alert('Account not found'); // Manejo de error si no hay cuenta
        }
      },
      error => {
        console.error('Login error:', error); // Log del error para depuración
        alert('Login failed: ' + (error.error?.message || 'Unknown error')); // Muestra mensaje de error
      }
    );
  }

  onRegister() {
    this.router.navigate(['/register']);
  }
}