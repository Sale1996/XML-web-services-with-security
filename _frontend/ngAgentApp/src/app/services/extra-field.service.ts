import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ExtraField } from '../model/extra-field.model';


const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class ExtraFieldService {

    constructor(private http: HttpClient) { }

    getExtraFields(): Observable<ExtraField[]> {
        return this.http.get<ExtraField[]>(environment.apiUrlExtraField);
    }

    getExtraFieldsByUnit(id: number): Observable<ExtraField[]> {
        return this.http.get<ExtraField[]>(environment.apiUrlExtraField + '/unit/' + id);
    }

}