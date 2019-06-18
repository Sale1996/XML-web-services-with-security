import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, Route } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {


  constructor(
    private router: Router,
    private authService: AuthService
  ) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if (!localStorage.getItem('access_token') || this.authService.isTokenExpired(localStorage.getItem('access_token'))) {
      localStorage.clear();
      if (state.url === '/login') {
        return true;
      } else {
        this.router.navigate(['/login']);
        return false;
      }
    } else {
      if (state.url === '/login') {
        this.router.navigate(['/accommodation']);
        return false;
      }
      return true;
    }
  }

}
