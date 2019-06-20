import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Reservation } from '../model/reservation.model';


const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class ReservationService {

    constructor(private http: HttpClient) { }


    getReservations(): Observable<Reservation[]> {
        return this.http.get<Reservation[]>(environment.apiUrlReservation);
    }


    confirmReservation(id): Observable<Boolean> {
        return this.http.put<Boolean>(environment.apiUrlReservation + '/confirm', id, httpOptions);
    }

    getOccupancies(id: number): Observable<Reservation[]> {
        return this.http.get<Reservation[]>(environment.apiUrlReservation + '/occupancy/' + id);
    }

    createOccupancy(occupancy: Reservation): Observable<Reservation> {
        return this.http.post<Reservation>(environment.apiUrlReservation, occupancy, httpOptions);
    }

    deleteOccupancy(id: number): Observable<Reservation> {
        return this.http.delete<Reservation>(environment.apiUrlReservation + '/' + id);
    }
}