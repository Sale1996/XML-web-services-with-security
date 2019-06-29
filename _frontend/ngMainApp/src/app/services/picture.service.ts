import { Picture } from './../model/picture.model';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PictureService {

  constructor(private http: HttpClient) { }

  getPictures(id: number): Observable<Picture[]> {
    return this.http.get<Picture[]>(environment.apiPicture + '/accommodations/' + id);
  }
}
