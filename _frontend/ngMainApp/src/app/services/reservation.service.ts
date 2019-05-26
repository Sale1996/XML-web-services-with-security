import { Reservation } from './../model/reservation.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private reservationtUrl = 'http://localhost:8080/reservations';

  constructor(
    private http: HttpClient
  ) { }

  reserve(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(this.reservationtUrl + '/', reservation, httpOptions);
  }
}
