import { Component, OnInit } from '@angular/core';

import { ProductDetailsComponent } from '../product-details/product-details.component';
import { Observable } from "rxjs";
import { ProductService } from "../product.service";
import { Product, PageProduct } from "../product";
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[];

  constructor(private productService: ProductService,
    private router: Router) {}

  ngOnInit() {
    debugger;
    this.reloadData();
  }

  reloadData() {
    this.productService.getProductsList()
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
    this.productService.deleteProduct(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  productDetails(id: number){
    this.router.navigate(['details', id]);
  }

}
