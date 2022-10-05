import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-workspace',
  templateUrl: './workspace.component.html',
  styleUrls: ['./workspace.component.css']
})
export class WorkspaceComponent implements OnInit {
  auth:any;
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.setAuth();
  }
  logout(){
    this.authService.logout();
  }
  setAuth() {
   this.authService.getAuth().subscribe(data => {
    this.auth =  data;
   },err=>{
    console.log(err);
   })
  }
  
  

}
