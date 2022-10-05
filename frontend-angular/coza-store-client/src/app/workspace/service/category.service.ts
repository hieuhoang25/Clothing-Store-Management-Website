import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
 
  url = 'http://localhost:8080/api/v1/admin/';
  url_size = this.url + 'sizes';
  url_category = this.url + 'categories';
  url_color = this.url + 'colors';
  url_tag = this.url + 'tags';
  constructor(private http: HttpClient) {}

  deleteCategory(id: string) {
    return this.http.delete(this.url_category + '/' + id);
  }
  deleteColor(id: string) {
    return this.http.delete(this.url_color + '/' + id);
  }
  deleteTag(id: string) {
    return this.http.delete(this.url_tag + '/' + id);
  }
  deleteSize(id: string) {
    return this.http.delete(this.url_size + '/' + id);
  }
  addCategory(data: any) {
    return this.http.post(this.url_category, data);
  }
  addColor(data: any) {
    return this.http.post(this.url_color, data);
  }
  addSize(data: any) {
    return this.http.post(this.url_size, data);
  }
  addTag(data: any) {
    return this.http.post(this.url_tag, data);
  }
}
