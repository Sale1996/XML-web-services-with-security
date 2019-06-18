import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Type } from '../model/type.model';


const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class TypeService {

    constructor(private http: HttpClient) { }

    getTypes(): Observable<Type[]> {
        return this.http.get<Type[]>(environment.apiUrlType);
    }


}