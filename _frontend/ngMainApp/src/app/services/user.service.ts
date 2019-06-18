import { environment } from './../../environments/environment';
import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = environment.apiUrlUser;

  constructor(private http: HttpClient) {
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(this.userUrl + '/' + user.id, user, httpOptions);
  }

  getUserByEmail(e: string): Observable<User> {
    return this.http.get<User>(this.userUrl + '/email/' + e);
  }
}
