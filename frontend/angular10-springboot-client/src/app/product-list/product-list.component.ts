import { Component, OnInit } from '@angular/core';

import { ProductDetailsComponent } from '../product-details/product-details.component';
import { Observable } from "rxjs";
import { ProductService } from "../product.service";
import { Product, PageProduct, Category } from "../product";
import { Router } from '@angular/router';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[];
  name: String;
  queryField: FormControl = new FormControl();
  categorys = Category;

  constructor(private productService: ProductService,
    private router: Router) { }

  ngOnInit() {
    debugger;
    this.reloadData();

    this.queryField.valueChanges
      .subscribe(queryField => this.reloadDataByName(queryField));
  }

  reloadData() {
    this.productService.getProductsList()
    .subscribe((result: PageProduct) => {
      if (result !== undefined &&
        result.content !== undefined && result.content.length > 0) {
        this.products = result.content;
      } else {
        this.products = [];
      }
    });
  }

  reloadDataByName(queryField:string) {
    if (queryField === undefined || queryField === null || queryField === ""){
      this.reloadData();
    } else {
      this.productService.getProductsByName(queryField)
      .subscribe((result: PageProduct) => {
        if (result !== undefined &&
            result.content !== undefined && result.content.length > 0){
          this.products = result.content;
        } else {
          this.products = [];
        }
      });
    }
  }

  deleteProduct(id: number) {

    const product = this.products.find(element => element.id === id);

    if(confirm("Are you sure to delete "+product.name)) {
      this.productService.deleteProduct(id)
      .subscribe(
        data => {
          console.log(data);
          if(this.queryField !== undefined && this.queryField.value !== ""){
            this.reloadDataByName(this.queryField.value);
          } else {
            this.reloadData();
          }
        },
        error => console.log(error));
    }
  }

  productDetails(id: number) {
    this.router.navigate(['details', id]);
  }
  productUpdate(id: number) {
    this.router.navigate(['update', id]);
  }

}
