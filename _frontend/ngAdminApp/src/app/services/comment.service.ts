import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from '../model/comment.model';


const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class CommentService {

    constructor(private http: HttpClient) { }

    getComments(): Observable<Comment[]> {
        return this.http.get<Comment[]>(environment.apiUrlComment + '/all');
    }


    verified(id: number) {
      return this.http.get(environment.apiUrlComment + '/verified/' + id);
  }

}
