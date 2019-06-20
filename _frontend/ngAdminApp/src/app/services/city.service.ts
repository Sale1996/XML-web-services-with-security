import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { City } from '../model/city.model';


const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class CityService {

    constructor(private http: HttpClient) { }


    getCities(): Observable<City[]> {
        return this.http.get<City[]>(environment.apiUrlCity);
    }

    deleteCity(id: number): Observable<City> {
        return this.http.delete<City>(environment.apiUrlCity + '/' + id);
    }

    createCity(city: City): Observable<City> {
        return this.http.post<City>(environment.apiUrlCity, city, httpOptions);
    }

    updateCity(city: City): Observable<City> {
        return this.http.put<City>(environment.apiUrlCity + '/' + city.cityId, city, httpOptions);
    }

    getCityById(id: number): Observable<City> {
        return this.http.get<City>(environment.apiUrlCity + '/' + id);
    }
}