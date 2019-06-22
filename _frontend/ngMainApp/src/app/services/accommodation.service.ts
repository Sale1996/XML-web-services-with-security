import { AccommodationUnit } from './../model/accommodation-unit.model';
import { AccommodationUnitsWith } from './../model/accommodation-units-with.model';
import { Search } from './../model/search.model';
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

  searchAccommotions(where: Number, guests: Number, search: Search): Observable<Accommodation[]> {
    return this.http.post<Accommodation[]>(this.accommodationtUrl + '/search/' + where + '/' + guests, search, httpOptions);
  }

  getCities(): Observable<City[]> {
    return this.http.get<City[]>(this.citiestUrl);
  }

  sort(accwithObj: AccommodationUnitsWith, id: number): Observable<AccommodationUnitsWith> {
    return this.http.post<AccommodationUnitsWith>(this.accommodationtUrl + '/sort/' + id, accwithObj, httpOptions);
  }



}
