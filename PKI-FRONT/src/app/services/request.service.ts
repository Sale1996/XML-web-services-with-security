import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Request } from '../model/request.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(
    private http: HttpClient
  ) { }

  getAllRequests(): Observable<Request[]> {
    return this.http.get<Request[]>(`${environment.apiUrl}` + '/request/');
  }
}
