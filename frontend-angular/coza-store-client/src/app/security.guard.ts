import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import decode from 'jwt-decode';
import { WhitepageComponent } from './whitepage/whitepage.component';
@Injectable({
  providedIn: 'root'
})
export class SecurityGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router){

  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      const expectedRole = route.data['role'];
      const access_token = localStorage.getItem('access_token');
      var tokenPayLoad: any;
      if (access_token) {
         tokenPayLoad = decode(access_token);
         console.log(tokenPayLoad);
      }
      
      if (!this.authService.isAuthenticated()){
        this.authService.logout()
      return false;
    }
    else{
        if (tokenPayLoad.roles[0]===expectedRole){
          return true;
        }
        else{
          route.component = WhitepageComponent
          return true;
        }
    }
      return true;
  }
  
}
