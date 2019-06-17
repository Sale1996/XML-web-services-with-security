import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ngAdminApp';

  isLoggedIn(): boolean {
    if (localStorage.hasOwnProperty('access_token')) {
      return true;
    } else {
      return false;
    }
  }
}
