import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Message } from '../model/message.model';


const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class MessageService {

    constructor(private http: HttpClient) { }

    getMessages(): Observable<Message[]> {
        return this.http.get<Message[]>(environment.apiUrlMessage);
    }

    createMessage(message: Message): Observable<Message> {
        return this.http.post<Message>(environment.apiUrlMessage, message, httpOptions);
    }
}