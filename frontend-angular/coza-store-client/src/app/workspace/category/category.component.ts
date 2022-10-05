import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { NotificationsService } from 'angular2-notifications';
import { Apollo, gql, QueryRef } from 'apollo-angular';
import { map, Observable, Subscription } from 'rxjs';
import { CategoryService } from '../service/category.service';
@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent implements OnInit, OnDestroy {
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
  data: any;
  nameOfType:string="";
  categories: any;
  tags: any;
  colors: any;
  sizes: any;
  queryRef!: QueryRef<any>;
  private querySubscription!: Subscription;
  input = {
    type: '',
    id: '',
    name: '',
  };
  type:any;
  constructor(
    private apollo: Apollo,
    private categoryService: CategoryService,
    private service:NotificationsService
  ) {}
  ngOnDestroy(): void {
    this.querySubscription.unsubscribe();
  }
  onSuccess(message:string) {
    this.service.success("Sucesss", message, {
      position:['top','right'],
      timeOut:1000,
      animated:'fade',
      showProgressBar:false
    })
  }
  onError(message:string) {
    this.service.error("Error", message, {
      position:['top','right'],
      timeOut:1000,
      animated:'fade',
      showProgressBar:false
    })
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
  refresh() {
    this.queryRef.refetch();
  }
  showNewPopup(type:string){
    this.type = type;
  }

  showPopup(type: string, id: string, name: string) {
    this.input = {
      type: type,
      id: id,
      name: name,
    };
  }
  delete(type: string, id: string) {
    switch (type) {
      case 'category':
        this.categoryService.deleteCategory(id).subscribe(
          (data) => {
            this.onSuccess("Delete category successfully!")
            this.refresh();
         
          },
          (err) => {
            this.onError("Error deleting category!");
            console.log(err);
           
          }
        );
        break;
      case 'color':
        this.categoryService.deleteColor(id).subscribe(
          (data) => {
            this.onSuccess("Delete color successfully!")
            this.refresh();
         
          },
          (err) => {
            this.onError("Error deleting color!");
            console.log(err);
         
          }
        );
        break;
      case 'size':
        this.categoryService.deleteSize(id).subscribe(
          (data) => {
            this.onSuccess("Delete size successfully!")
            this.refresh();
           
          },
          (err) => {
            this.onSuccess("Delete size failed!");
            console.log(err);
          
          }
        );
        break;
      case 'tag':
        this.categoryService.deleteTag(id).subscribe(
          (data) => {
            this.onSuccess("Delete tag successfully!")
            this.refresh();
       
          },
          (err) => {
            this.onError("Delete tag failed!");
            console.log(err);
 
          }
        );
        break;
    }
  }
  clear(){
    this.nameOfType="";
  }
  addNew(type: string){
    const data={
      name:this.nameOfType
    };
    switch (type) {
      case 'category':
       
        this.categoryService.addCategory(data).subscribe(
          (data) => {
            this.refresh();
            this.nameOfType="";
            this.onSuccess("Sucesssfully Added!")
          },
          (err) => {
            console.log(err);
            this.onError("Category already exists!")
          }
        );
        break;
      
      case 'size':
        
        this.categoryService.addSize(data).subscribe(
          (data) => {
            this.refresh();
            this.nameOfType="";
            this.onSuccess("Sucesssfully Added!")
          },
          (err) => {
            console.log(err);
            this.onError("Category already exists!")
          }
        );
        break;
      case 'tag':
        this.categoryService.addTag(data).subscribe(
          (data) => {
            this.refresh();
            this.nameOfType="";
            this.onSuccess("Sucesssfully Added!")
          },
          (err) => {
            console.log(err);
            this.onError("Category already exists!")
          }
        );
        break;
    }
  }
  
}
