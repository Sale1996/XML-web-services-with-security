import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Agent } from '../model/agent.model';
import { UpdatePassword } from '../model/update-password.model';


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


    changePassword(password: UpdatePassword): Observable<boolean> {
        return this.http.put<boolean>(environment.apiUrlAgent + '/pass', password, httpOptions);
    }

}