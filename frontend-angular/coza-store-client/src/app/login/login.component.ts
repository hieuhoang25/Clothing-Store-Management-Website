import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { 

  }

  ngOnInit(): void {
  }
  logIn(data:any): void {
      this.authService.login(data.username,data.password).subscribe(res => {
          localStorage.setItem('access_token',res.access_token);
          localStorage.setItem('refresh_token',res.refresh_token);
        this.router.navigateByUrl("/workspace")
      }, err =>{
       alert("Please recheck your username and password");
      })
  }
}
