import { Component, OnInit } from '@angular/core';
import { Product, Category } from '../product';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  id: number;
  product: Product;
  submitted: Boolean = false;
  registerForm: FormGroup;
  keys = Object.keys;
  categorys = Category;

  constructor(private route: ActivatedRoute,private router: Router,
    private productService: ProductService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.product = new Product();

    this.id = this.route.snapshot.params['id'];

    this.productService.getProduct(this.id)
      .subscribe(data => {
        console.log(data)
        this.product = data;
      }, error => console.log(error));

    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      category: ['', Validators.required],
      price: ['', Validators.required]
      }, {});
  }

  get f() { return this.registerForm.controls; }

  updateProduct() {
    this.product.id = this.id;
    this.productService.updateProduct(this.product)
      .subscribe(data => {
        console.log(data);
        this.product = new Product();
        this.submitted = true;
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {

    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }

    this.updateProduct();
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
