import { ExtraField } from './../model/extra-field.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ExtraFieldService {

  private extrafieldUrl = environment.apiExtraField;

  constructor(private http: HttpClient) { }

  getExtraFields(): Observable<ExtraField[]> {
    return this.http.get<ExtraField[]>(this.extrafieldUrl);
  }
}
