import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CatalogComponent } from './catalog/catalog.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterModule,
    FormsModule,
    HttpClientModule,
    LoginComponent,
    RegisterComponent,
    CatalogComponent
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mercadito-web';
}
