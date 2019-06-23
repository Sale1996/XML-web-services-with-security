import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AccommodationUnit } from '../model/accommodation-unit.model';


const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class AccommodationUnitService {

    constructor(private http: HttpClient) { }


    getUnits(id: number): Observable<AccommodationUnit[]> {
        return this.http.get<AccommodationUnit[]>(environment.apiUrlAccommodationUnit + '/' + id);
    }

    deleteUnit(id: number): Observable<AccommodationUnit> {
        return this.http.delete<AccommodationUnit>(environment.apiUrlAccommodationUnit + '/' + id);
    }

    createUnit(unit: AccommodationUnit): Observable<AccommodationUnit> {
        return this.http.post<AccommodationUnit>(environment.apiUrlAccommodationUnit, unit, httpOptions);
    }

    updateUnit(unit: AccommodationUnit): Observable<AccommodationUnit> {
        return this.http.put<AccommodationUnit>(environment.apiUrlAccommodationUnit + '/' + unit.accommodationUnitId, unit, httpOptions);
    }

    addExtraFieldToUnit(unitId: number, extraFieldId: number) {
        return this.http.put<AccommodationUnit>(environment.apiUrlAccommodationUnit + '/addService/' + unitId + '/' + extraFieldId, httpOptions);
    }

    removeExtraFieldFromUnit(unitId: number, extraFieldId: number) {
        return this.http.put<AccommodationUnit>(environment.apiUrlAccommodationUnit + '/removeService/' + unitId + '/' + extraFieldId, httpOptions);
    }

}