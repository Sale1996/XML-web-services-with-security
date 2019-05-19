import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { AccommodationCategory } from '../model/accommodation-category.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Category':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) {}

  getCategories(): Observable<AccommodationCategory[]> {
    return this.http.get<AccommodationCategory[]>(environment.apiUrlCategory);
  }

  getCategoryById(id: number): Observable<AccommodationCategory> {
    return this.http.get<AccommodationCategory>(environment.apiUrlCategory + '/' + id);
  }

  createCategory(category: AccommodationCategory): Observable<AccommodationCategory> {
    return this.http.post<AccommodationCategory>(environment.apiUrlCategory, category, httpOptions);
  }

  updateCategory(category: AccommodationCategory): Observable<AccommodationCategory> {
    return this.http.put<AccommodationCategory>(environment.apiUrlCategory + '/' + category.categoryId, category, httpOptions);
  }

  deleteCategory(id: number): Observable<AccommodationCategory> {
    return this.http.delete<AccommodationCategory>(environment.apiUrlCategory + '/' + id);
  }
}