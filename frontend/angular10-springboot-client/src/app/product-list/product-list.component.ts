import { Filter } from './../filter';
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
  queryFieldName: FormControl = new FormControl();
  queryFieldCategory: FormControl = new FormControl();
  categorys = Category;
  keys = Object.keys;

  filter: Filter =  new Filter;

  constructor(private productService: ProductService,
    private router: Router) { }

  ngOnInit() {
    debugger;
    this.reloadData();

    this.queryFieldName.valueChanges
      .subscribe(queryFieldName => this.reloadDataByName(queryFieldName));
    this.queryFieldCategory.valueChanges
      .subscribe(queryFieldCategory => this.reloadDataByCategory(queryFieldCategory));
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

  reloadDataByName(queryFieldName:string) {
    this.filter.name = queryFieldName;
    this.reloadDataByFilter();
  }

  reloadDataByCategory(queryFieldCategory:Category) {
    this.filter.category = queryFieldCategory;
    this.reloadDataByFilter();
  }

  reloadDataByFilter(){
    this.productService.getProductsByFilters(this.filter)
    .subscribe((result: PageProduct) => {
      if (result !== undefined &&
          result.content !== undefined && result.content.length > 0){
        this.products = result.content;
      } else {
        this.products = [];
      }
    });
  }


  deleteProduct(id: number) {

    const product = this.products.find(element => element.id === id);

    if(confirm("Are you sure to delete "+product.name)) {
      this.productService.deleteProduct(id)
      .subscribe(
        data => {
          this.reloadDataByFilter();
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
