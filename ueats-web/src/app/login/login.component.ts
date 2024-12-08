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
        if (response.accountId) {
          localStorage.setItem('accountId', response.accountId.toString());
          this.http.get<any>(`http://localhost:8081/profile?accountId=${response.accountId}`).subscribe(
            profileResponse => {
              localStorage.setItem('userName', profileResponse.name);
              localStorage.setItem('userCity', profileResponse.city);
  
              // Aquí rediriges al catálogo
              this.router.navigate(['/catalog']);
            },
            error => {
              console.error('Error fetching profile:', error);
              alert('Failed to fetch user profile');
            }
          );
        } else {
          alert('Account not found');
        }
      },
      error => {
        console.error('Login error:', error);
        alert('Login failed: ' + (error.error?.message || 'Unknown error'));
      }
    );
  }
  

  onRegister() {
    this.router.navigate(['/register']);
  }
}