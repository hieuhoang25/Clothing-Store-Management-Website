import { Component, OnDestroy, OnInit } from '@angular/core';
import { Apollo, gql, QueryRef } from 'apollo-angular';
import { Subscription } from 'rxjs';
import { ProductService } from '../service/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit, OnDestroy {
  query = gql`
    query {
      colors {
        id
        name
        decode
      }
      categories {
        id
        name
      }
      tags {
        id
        name
      }
      sizes {
        id
        name
      }
    }
  `;
  products: any;
  idDelete: any;
  product_colors: any = [
    // {
    //   color: {
    //     id: '6326cfcfa28f3c39f50ade47',
    //     name: 'Green',
    //     decode: '#00AD5F',
    //   },
    //   image: 'IPhone.png',
    // },
  ];
  product_sizes: any = [
    // {
    //   size: {
    //     id: '6326d2fe87863c073cdf0ac0',
    //     name: 'XS',
    //   },
    //   quality: 150,
    // },
  ];
  selectedFiles?: FileList;
  queryRef!: QueryRef<any>;
  categories: any;
  sizes: any;
  colors: any;
  tags: any;
  private querySubscription!: Subscription;
  constructor(private productService: ProductService, private apollo: Apollo) {
    this.productService.fetchAll().subscribe(
      (data) => {
        this.products = data;
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }
  ngOnDestroy(): void {
    this.querySubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.queryRef = this.apollo.watchQuery<any>({
      query: this.query,
      pollInterval: 500,
    });
    this.querySubscription = this.queryRef.valueChanges.subscribe(
      ({ data, loading }) => {
        console.log(data);
        this.categories = data.categories;
        this.colors = data.colors;
        this.sizes = data.sizes;
        this.tags = data.tags;
      }
    );
  }
  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }
  upload() {}

  public onOpenModal(obj: any, mode: string): void {
    const button = document.createElement('button');
    const container = document.getElementById('main-container');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-bs-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-bs-target', '#addProductModel');
    } else if (mode === 'edit') {
      button.setAttribute('data-bs-target', '#updateProductModel');
    } else if (mode === 'delete') {
      this.idDelete = obj;
      button.setAttribute('data-bs-target', '#deleteProductModel');
    }

    container?.appendChild(button);
    button.click();
  }
  delete(id: string): void {
    this.productService.delete(id).subscribe(
      (data) => {
        alert('Product deleted successfully');
      },
      (error) => {
        alert('Product deleted failed!');
      }
    );
  }
  newSizeForProduct(sizeforproduct: any, qualityofproduct: any) {
    // this.product_sizes.push({
    //   size: {
    //     id: '6326d2fe87863c073cdf0ac0',
    //     name: 'XS',
    //   },
    //   quality: 150,
    // });
    console.log(sizeforproduct.value + ': ' + qualityofproduct.value);
  }
  newColorForProduct(colorofproduct: any, fileofcolor: any) {
    console.log(colorofproduct.value + ': ' + fileofcolor.files[0].name);
  }

  deleteSize(id: string): void {}
}
