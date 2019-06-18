import { Component, OnInit } from '@angular/core';
import { Accommodation } from 'src/app/model/accommodation.model';
import { Observable } from 'rxjs';
import { AccommodationService } from 'src/app/services/accommodation.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  userEmail: string;

  constructor(private accommodationService: AccommodationService, private authService: AuthService) { }

  ngOnInit() {
    this.getAccommodationService();
    this.userEmail = this.authService.getEmailFromToken(localStorage.getItem('access_token'));

  }


  getAccommodationService() {
    this.accommodationService.getAccommodationById(1).subscribe(
      data => {
        localStorage.setItem('accommodation', data.accommodationId.toString());
      },
      error => {
        localStorage.removeItem('accommodation');
      }
    );
  }

  getLocalStorageItem() {
    return localStorage.getItem('accommodation');
  }

}
