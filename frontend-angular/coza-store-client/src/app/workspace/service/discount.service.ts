import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {
  api_url = environment.apiUrl;
  url = this.api_url +'/api/v1/admin/coupons'
  constructor(private http : HttpClient) { }
  findAll(){
    return this.http.get(this.url);
  }
  insert(data:any){
    return this.http.post(this.url, data);
  }
  delete(id:string){
    return this.http.delete(this.url+`/${id}`);
  }
  findOne(id:string){
  return this.http.get<any>(this.url+`/${id}`);
  }
  update(data:any){
    return this.http.put(this.url, data);
  }
}
