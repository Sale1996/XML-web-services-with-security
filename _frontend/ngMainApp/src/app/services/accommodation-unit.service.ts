import { AccommodationUnitsWith } from './../model/accommodation-units-with.model';
import { AccommodationUnit } from './../model/accommodation-unit.model';
import { Search } from './../model/search.model';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AccommodationUnitService {

  private accommodationtUrl = environment.apiAccomodations;

  constructor(
    private http: HttpClient
  ) { }


  getAccommotionUnits(id: number, search: Search): Observable<AccommodationUnitsWith> {
    return this.http.post<AccommodationUnitsWith>(this.accommodationtUrl + '/searchUnits/' + id, search, httpOptions);
  }


}
