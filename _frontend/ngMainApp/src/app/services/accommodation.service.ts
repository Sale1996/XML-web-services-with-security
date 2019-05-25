import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Accommodation } from './../model/accommodation.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AccommodationService {

  private accommodationtUrl = 'http://localhost:8080/accommodations';

  constructor(
    private http: HttpClient
  ) { }

  searchAccommotions(where: String, checkin: Number, checkout: Number, guests: Number, rooms: Number): Observable<Accommodation[]> {
    return this.http.get<Accommodation[]>(this.accommodationtUrl + '/search?where=' + where +
    '&checkin=' + checkin + '&checkout=' + checkout + '&guests=' + guests + '&rooms=' + rooms);
  }


}
