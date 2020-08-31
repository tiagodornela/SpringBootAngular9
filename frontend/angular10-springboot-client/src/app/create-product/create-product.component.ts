import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Product, Category } from '../product';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {

  product: Product = new Product();
  submitted = false;
  registerForm: FormGroup;
  keys = Object.keys;
  categorys = Category;

  constructor(private productService: ProductService,
    private router: Router, private formBuilder: FormBuilder) {
    }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      category: ['', Validators.required],
      price: ['', Validators.required]
      }, {});
  }

  get f() { return this.registerForm.controls; }

  newProduct(): void {
    this.submitted = false;
    this.product = new Product();
  }

  save() {
    this.productService
    .createProduct(this.product).subscribe(data => {
      console.log(data)
      this.product = new Product();
      this.gotoList();
    },
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
    this.save();
  }

  gotoList() {
    this.router.navigate(['/products']);
  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
    this.gotoList();
  }
}
