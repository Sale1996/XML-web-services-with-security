import { HttpClient, HttpHeaders, HttpHeaderResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import * as jwt_decode from 'jwt-decode';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Credentials } from './../model/credentials.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  observe: 'response' as 'response'
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    private router: Router
    ) { }

  login(credentials: Credentials) {
      return this.http.post(`${environment.apiUrlAuth}auth`, credentials, httpOptions).pipe(map(((res: HttpResponse<any>) => {
        localStorage.setItem('access_token', JSON.stringify(res.headers.get('Authorization').split(' ')[1]));
        this.router.navigate(['/code-book']);
      })));
  }

  logout() {
      return this.http.get(`${environment.apiUrlAuth}/logout`, httpOptions);
  }

  getTokenExpirationDate(token: string): Date {
    const decoded = jwt_decode(token);

    if (decoded.exp === undefined) {
      return null;
    }

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

  isTokenExpired(token: string): boolean {

    const date = this.getTokenExpirationDate(token);

    if (date === undefined) {
      return false;
    } else {
      return !(date.valueOf() > new Date().valueOf());
    }
  }

  getEmailFromToken(token: string): string {
    const decoded = jwt_decode(token);

    if (decoded.sub === undefined) {
      return '';
    }

    return decoded.sub;
  }
}
