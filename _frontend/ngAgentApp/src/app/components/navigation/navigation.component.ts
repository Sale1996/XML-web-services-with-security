import { Component, OnInit } from '@angular/core';
import { Accommodation } from 'src/app/model/accommodation.model';
import { Observable } from 'rxjs';
import { AccommodationService } from 'src/app/services/accommodation.service';
import { AuthService } from 'src/app/services/auth.service';
import { AgentService } from 'src/app/services/agent.service';
import { Agent } from 'src/app/model/agent.model';
import { NgLocalization } from '@angular/common';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  userEmail: string;
  userId: number;

  constructor(private accommodationService: AccommodationService, private authService: AuthService, private agentService: AgentService) { }

  ngOnInit() {
    this.userEmail = this.authService.getEmailFromToken(localStorage.getItem('access_token'));
    this.getAgentId();
  }

  getAgentId() {
    const id = this.agentService.getAgentByEmail(this.userEmail).subscribe(
      response => {
        this.userId = (response as Agent).id;
        localStorage.setItem('agent', this.userId.toString());
        this.getAccommodationService();
      }
    );
  }


  getAccommodationService() {
    this.accommodationService.getAccommodationById(this.userId).subscribe(
      data => {
        localStorage.setItem('accommodation', this.userId.toString());
      },
      error => {
        localStorage.removeItem('accommodation');
      }
    );
  }

  getLocalStorageItem() {
    return localStorage.getItem('accommodation');
  }

  logout() {
    this.authService.logout();
    
  }

}
