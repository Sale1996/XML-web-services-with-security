import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Admin } from '../model/admin.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) {}

  getAdmins(): Observable<Admin[]> {
    return this.http.get<Admin[]>(environment.apiUrlAdmin);
  }

  createAdmin(admin: Admin): Observable<Admin> {
    return this.http.post<Admin>(environment.apiUrlAdmin, admin, httpOptions);
  }

  deleteAdmin(id: number): Observable<Admin> {
    return this.http.delete<Admin>(environment.apiUrlAdmin + '/' + id);
  }
}
