import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { AccommodationService } from '../model/accommodation-service.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-AdditionalService':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AdditionalService {

  constructor(private http: HttpClient) {}

  getAdditionalServices(): Observable<AccommodationService[]> {
    return this.http.get<AccommodationService[]>(environment.apiUrlAdditionalService);
  }

  getAdditionalServiceById(id: number): Observable<AccommodationService> {
    return this.http.get<AccommodationService>(environment.apiUrlAdditionalService + '/' + id);
  }

  createAdditionalService(service: AccommodationService): Observable<AccommodationService> {
    return this.http.post<AccommodationService>(environment.apiUrlAdditionalService, service, httpOptions);
  }

  updateAdditionalService(service: AccommodationService): Observable<AccommodationService> {
    return this.http.put<AccommodationService>(environment.apiUrlAdditionalService + '/' + service.extraFieldId, service, httpOptions);
  }

  deleteAdditionalService(id: number): Observable<AccommodationService> {
    return this.http.delete<AccommodationService>(environment.apiUrlAdditionalService + '/' + id);
  }
}
