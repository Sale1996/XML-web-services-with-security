import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Price } from '../model/price.model';


const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class PriceService {

    constructor(private http: HttpClient) { }


    getPrices(id: number): Observable<Price[]> {
        return this.http.get<Price[]>(environment.apiUrlPrice + '/' + id);
    }

    deletePrice(id: number): Observable<Price> {
        return this.http.delete<Price>(environment.apiUrlPrice + '/' + id);
    }

    createPrice(price: Price): Observable<Price> {
        return this.http.post<Price>(environment.apiUrlPrice, price, httpOptions);
    }


}