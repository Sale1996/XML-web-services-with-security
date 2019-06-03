import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Accommodation } from '../model/accommodation.model';


const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class AccommodationService {

    constructor(private http: HttpClient) { }



    getAccommodationById(id: number): Observable<Accommodation> {
        return this.http.get<Accommodation>(environment.apiUrlAccommodation + '/' + id);
    }

    createAccommodation(accommodation: Accommodation): Observable<Accommodation> {
        return this.http.post<Accommodation>(environment.apiUrlAccommodation, accommodation, httpOptions);
    }

    updateAccommodation(accommodation: Accommodation): Observable<Accommodation> {
        return this.http.put<Accommodation>(environment.apiUrlAccommodation + '/' + accommodation.accommodationId, accommodation, httpOptions);
    }


}