import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Agent } from '../model/agent.model';


const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class AgentService {

    constructor(private http: HttpClient) { }


    getAgentById(id: number): Observable<Agent> {
        return this.http.get<Agent>(environment.apiUrlAgent + "/" + id);
    }

    updateAgent(agent: Agent): Observable<Agent> {
        return this.http.put<Agent>(environment.apiUrlAgent + "/" + agent.id, agent, httpOptions);
    }


    changePassword(agent: Agent): Observable<Agent> {
        return this.http.put<Agent>(environment.apiUrlAgent + '/pass/' + agent.id, agent, httpOptions);
    }

}