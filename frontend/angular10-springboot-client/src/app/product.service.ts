import { Category } from './product';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/api/product/v1';

  constructor(private http: HttpClient) { }

  getProduct(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createProduct(product: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, product);
  }

  updateProduct(product: Object): Observable<Object> {
    return this.http.put(`${this.baseUrl}`, product);
  }

  deleteProduct(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getProductsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getProductsByName(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/findProductByName/${name}`);
  }

  getProductsByFilters(filter: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/findProductByFilter`, filter);
  }
}
