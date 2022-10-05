import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private jwtHepler:JwtHelperService,private router: Router) { }
  public login(username: string, password: string) {
    const data = {
      username: username,
      password: password
    }
    return this.http.post<any>(environment.apiUrl+"/login", data);
  }
  public logout(){
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    this.router.navigateByUrl("/")
  }
  public isAuthenticated(){
      const token = localStorage.getItem('refresh_token');
      if (token==null) return false;
      return !this.jwtHepler.isTokenExpired(token);
  }
 
  public getAuth() {
    const token = localStorage.getItem('access_token');
    const headers = new HttpHeaders();
    headers.append('Authorization', 'Bearer ' + token);
    return this.http.get(environment.apiUrl+"/api/v1/user/myprofile",{
      headers: headers
    });
  }
}
