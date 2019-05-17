import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Agent } from './../model/agent.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  constructor(private http: HttpClient) {}

  getAgents(): Observable<Agent[]> {
    return this.http.get<Agent[]>(environment.apiUrlAgent);
  }

  createAgent(agent: Agent): Observable<Agent> {
    return this.http.post<Agent>(environment.apiUrlAgent, agent, httpOptions);
  }

  deleteAgent(id: number): Observable<Agent> {
    return this.http.delete<Agent>(environment.apiUrlAgent + '/' + id);
  }

  changeStatusAgent(agent: Agent): Observable<boolean> {
    return this.http.put<boolean>(environment.apiUrlAgent + '/status/' + agent.id, agent, httpOptions);
  }
}
