import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
  })
export class JwtInterceptor implements HttpInterceptor {

    constructor(private router: Router) { }

    handleAuthError(err: HttpErrorResponse): Observable<any> {
        // handle your auth error or rethrow
        if (err.status === 401) {
            // navigate /delete cookies or whatever
            localStorage.clear();
            this.router.navigate(['/login']);
            // if you've caught / handled the error, you don't want to rethrow it 
            // unless you also want downstream consumers to have to handle it as well.
            return of(err.message);
        }
        return throwError(err);
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        // add authorization header with jwt token if available
        let access_token = JSON.parse(localStorage.getItem('access_token'));
        if (access_token) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${access_token}`
                }
            });
        }

        return next.handle(request).pipe(catchError(x=> this.handleAuthError(x)));
    }
}