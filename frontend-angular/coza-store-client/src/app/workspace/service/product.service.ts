import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  url = environment.apiUrl+"/api/v1/";
  constructor(private http: HttpClient) {}

  fetchAll(){
    return this.http.get(this.url+"products");
  }
  fetchById(id:string){
    return this.http.get<any>(this.url+"products/"+id);
  }
  insert(data:any){
    return this.http.post(this.url+"admin/products",data);
  }
  update(data:any){
    return this.http.put(this.url+"admin/products",data);
  }
  delete(id:string){
    return this.http.delete(this.url+"admin/products/"+id);
  }
}
