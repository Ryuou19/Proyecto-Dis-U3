import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  storeId: number = 0;
  products: any[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    // Obtener el storeId desde los parámetros de la ruta
    this.storeId = +this.route.snapshot.paramMap.get('id')!;
    this.getProductsByStore(this.storeId);
  }

  getProductsByStore(storeId: number): void {
    // Realizar la solicitud al backend para obtener los productos de la tienda
    this.http.get<any[]>(`http://localhost:8082/products?storeId=${storeId}`).subscribe(
      response => {
        console.log('Productos recibidos:', response);
        this.products = response;
      },
      error => {
        console.error('Error al obtener los productos:', error);
      }
    );
  }
}
