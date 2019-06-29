import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  userEmail: string;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.userEmail = this.authService.getEmailFromToken(localStorage.getItem('access_token'));
  }

  isLoggedIn(): boolean {
    if (localStorage.hasOwnProperty('access_token')) {
      return true;
    } else {
      return false;
    }
  }

  logout() {
    this.authService.logout();

  }

}
