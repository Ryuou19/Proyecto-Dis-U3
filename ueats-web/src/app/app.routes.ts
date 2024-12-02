import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CatalogComponent } from './catalog/catalog.component';
import { ProductsComponent } from './products/products.component';
import { OrdersComponent } from './orders/orders.component';

export const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'catalog', component: CatalogComponent },
    { path: 'products', component: ProductsComponent },
    { path: 'orders', component: OrdersComponent }
];