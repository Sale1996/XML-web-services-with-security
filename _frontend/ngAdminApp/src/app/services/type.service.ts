import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { AccommodationType } from '../model/accommodation-type.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(private http: HttpClient) {}

  getTypes(): Observable<AccommodationType[]> {
    return this.http.get<AccommodationType[]>(environment.apiUrlType);
  }

  getTypeById(id: number): Observable<AccommodationType> {
    return this.http.get<AccommodationType>(environment.apiUrlType + '/' + id);
  }

  createType(type: AccommodationType): Observable<AccommodationType> {
    return this.http.post<AccommodationType>(environment.apiUrlType, type, httpOptions);
  }

  updateType(type: AccommodationType): Observable<AccommodationType> {
    return this.http.put<AccommodationType>(environment.apiUrlType + '/' + type.typeId, type, httpOptions);
  }

  deleteType(id: number): Observable<AccommodationType> {
    return this.http.delete<AccommodationType>(environment.apiUrlType + '/' + id);
  }
}
