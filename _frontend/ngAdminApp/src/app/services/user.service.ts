import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { User } from '../model/user.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(environment.apiUrlUser);
  }

  deleteUser(id: number): Observable<User> {
    return this.http.delete<User>(environment.apiUrlUser + '/' + id);
  }

  changeStatusUser(user: User): Observable<boolean> {
    return this.http.put<boolean>(environment.apiUrlUser + '/status/' + user.id, user, httpOptions);
  }
}
