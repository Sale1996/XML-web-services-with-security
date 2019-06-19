import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Picture } from '../model/picture.model';


const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class PictureService {

    constructor(private http: HttpClient) { }

    createPicture(picture: Picture): Observable<Picture> {
        return this.http.post<Picture>(environment.apiUrlPicture, picture, httpOptions);
    }

    getPicture(): Observable<Picture[]> {
        return this.http.get<Picture[]>(environment.apiUrlPicture);
    }

    deletePicture(id: number): Observable<Picture> {
        return this.http.delete<Picture>(environment.apiUrlPicture + '/' + id);
    }


}