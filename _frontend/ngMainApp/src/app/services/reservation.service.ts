import { environment } from './../../environments/environment';
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

  private reservationtUrl = environment.apiReservations;

  constructor(
    private http: HttpClient
  ) { }

  reserve(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(this.reservationtUrl, reservation, httpOptions);
  }

  getReservation(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(environment.apiReservations);
  }

  getReservationById(id: number): Observable<Reservation> {
    return this.http.get<Reservation>(environment.apiReservations + '/' + id);
  }

  getReservationByUserId(userId: number): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(environment.apiReservations + '/client/' + userId);
  }

  removeReservation(id: number) {
    return this.http.delete<Reservation>(this.reservationtUrl + '/' + id, httpOptions);
  }
}
