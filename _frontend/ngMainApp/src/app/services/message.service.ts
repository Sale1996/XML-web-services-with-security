import { Observable } from 'rxjs';
import { Message } from './../model/message.model';
import { environment } from './../../environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private messageUrl = environment.apiMessages;

  constructor(private http: HttpClient) { }

  sendMessage(message: Message): Observable<Message> {
    return this.http.post<Message>(this.messageUrl, message, httpOptions);
  }
  // zakuco sam id dok logovanje ne bude gotovo
  getMessages(): Observable<Message[]> {
    return this.http.get<Message[]>(environment.apiMessages + '/1');
  }
}
