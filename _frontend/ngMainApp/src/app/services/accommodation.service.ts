import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Accommodation } from './../model/accommodation.model';
import { City } from './../model/city.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AccommodationService {

  private accommodationtUrl = environment.apiAccomodations;
  private citiestUrl = environment.apiCities;

  constructor(
    private http: HttpClient
  ) { }

  searchAccommotions(where: String, checkin: Number, checkout: Number, guests: Number): Observable<Accommodation[]> {
    return this.http.get<Accommodation[]>(this.accommodationtUrl + '/search/' + where + '/' + guests);
  }

  getCities(): Observable<City[]> {
    return this.http.get<City[]>(this.citiestUrl);
  }


}
