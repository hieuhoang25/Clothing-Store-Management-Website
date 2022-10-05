import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { LoginComponent } from './login/login.component';
import { ForwardpassComponent } from './forwardpass/forwardpass.component';
import { CategoryComponent } from './workspace/category/category.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { GraphQLModule } from './graphql.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SimpleNotificationsModule } from 'angular2-notifications';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DatePipe } from '@angular/common';
import { JwtModule } from '@auth0/angular-jwt';
import { WhitepageComponent } from './whitepage/whitepage.component';
import { AuthService } from './auth.service';
import { AuthInterceptor } from './auth.interceptor';
export function tokenGetter() {
  return localStorage.getItem("access_token");
}
@NgModule({
  declarations: [
    AppComponent,

    LoginComponent,
    ForwardpassComponent,
    CategoryComponent,
    NotfoundComponent,
    WhitepageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    GraphQLModule,
    FormsModule,
    SimpleNotificationsModule.forRoot(),
    BrowserAnimationsModule,
    ReactiveFormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ["localhost:8080"],
        
      },
    })
   
  ],
  providers: [ DatePipe,
  AuthService,
  { 
    provide:HTTP_INTERCEPTORS,
    useClass:AuthInterceptor,
    multi:true
  }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
