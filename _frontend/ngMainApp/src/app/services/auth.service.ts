import { HttpClient, HttpHeaders, HttpHeaderResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import * as jwt_decode from 'jwt-decode';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Credentials } from '../model/credentials.model';
import { User } from '../model/user.model';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  observe: 'response' as 'response'
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private userUrl = environment.apiUrlUser;

  constructor(
    private http: HttpClient,
    private router: Router
    ) { }

  login(credentials: Credentials) {
      return this.http.post(`${environment.apiUrlAuth}auth`, credentials, httpOptions).pipe(map(((res: HttpResponse<any>) => {
        localStorage.setItem('access_token', JSON.stringify(res.headers.get('Authorization').split(' ')[1]));
        location.assign('/home');
      })));
  }

  register(user: User) {
    return this.http.post<User>(this.userUrl, user, httpOptions);
    // .pipe(map(((res: HttpResponse<any>) => {
    //   JSON.stringify(res.body);
    //   this.router.navigate(['/home']); // prebaciti na stranicu gde aktivira nalog
    // })));
  }

  logout() {

    localStorage.clear();
    location.assign('/');
      // return this.http.get(`${environment.apiUrlAuth}/logout`, httpOptions);
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

    if(token === null){
      return '';
    }

      const decoded = jwt_decode(token);

      if (decoded.sub === undefined) {
        return '';
      }

      return decoded.sub;


  }
}
