import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, finalize, Observable } from "rxjs";
import UrlPattern from "url-pattern";

@Injectable(
  {  providedIn: 'root'}
)
export class AuthInterceptor implements HttpInterceptor {
 
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
       const idToken = localStorage.getItem('access_token')
     const servletPath = req.url.substring('http://localhost:8080'.length);
     var pattern = new UrlPattern(/^\/api\/v1\/([^\/]+)/,
     ['resource']);
     console.log(pattern.match(servletPath));
      
       if (idToken) {
            const cloned = req.clone({
                headers: req.headers.set("Authorization","Bearer "+idToken)

            })
            return next.handle(cloned);
        }
        else{
            return next.handle(req);
        }
    }
    
}