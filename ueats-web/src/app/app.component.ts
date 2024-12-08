import { Component, OnInit, HostListener } from '@angular/core';
import { RouterModule, Router, NavigationEnd, Event } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterModule,
    FormsModule,
    HttpClientModule,
    CommonModule
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'utal-eats';
  userName: string | null = null;
  showUserMenu: boolean = false;
  isLoggedIn: boolean = false;
  currentRoute: string = '';

  constructor(private router: Router) {
    this.router.events.pipe(
      filter((event: Event): event is NavigationEnd => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.currentRoute = event.url;
  
      this.checkLoginStatus();
    });
  }
  

  ngOnInit() {
    this.checkLoginStatus();
    console.log('Init - isLoggedIn:', this.isLoggedIn);
  }

  checkLoginStatus() {
    const accountId = localStorage.getItem('accountId');
    const name = localStorage.getItem('userName');
    this.isLoggedIn = !!accountId;
    console.log('Checking login status - accountId:', accountId);

    if (name && this.isLoggedIn) {
      this.userName = name;
    } else {
      if (this.isLoggedIn) {
        this.userName = 'Usuario';
      }
    }
  }

  shouldShowMenu(): boolean {
    const shouldShow = this.currentRoute !== '/login' && 
                      this.currentRoute !== '/register' && 
                      this.currentRoute !== '/';
    console.log('Should show menu:', shouldShow);
    return shouldShow;
  }

  toggleUserMenu(event?: MouseEvent) {
    if (event) {
      event.stopPropagation();
    }
    this.showUserMenu = !this.showUserMenu;
  }

  viewOrders() {
    this.router.navigate(['/orders']);
    this.showUserMenu = false;
  }

  logout() {
    localStorage.removeItem('accountId');
    localStorage.removeItem('userName');
    localStorage.removeItem('cart');
    this.isLoggedIn = false;
    this.router.navigate(['/']);
    this.showUserMenu = false;
    this.checkLoginStatus();
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    if (this.showUserMenu) {
      const userDropdown = document.querySelector('.user-dropdown');
      if (userDropdown && !userDropdown.contains(event.target as Node)) {
        this.showUserMenu = false;
      }
    }
  }

  goToCatalog() {
    const accountId = localStorage.getItem('accountId');
    const name = localStorage.getItem('userName');
    this.isLoggedIn = !!accountId;
    console.log('Checking login status - accountId:', accountId);

    if (name && this.isLoggedIn) {
      this.router.navigate(['/catalog']);
    } else {
      if (this.isLoggedIn) {
        this.router.navigate(['/'])
      }
    }

  }
}