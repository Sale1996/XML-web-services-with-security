import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Rating } from '../model/rating.model';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private ratingUrl = environment.apiRating;

  constructor(private http: HttpClient) {
  }

  createRating(rating: Rating): Observable<Rating> {
    return this.http.post<Rating>(this.ratingUrl, httpOptions);
  }

}
