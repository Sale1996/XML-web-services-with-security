import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor() { }

  showSuccess(message: string): void {
    console.log('SUCCESS: ' + message);
  }

  showError(message: string): void {
    console.log('ERROR: ' + message);
  }
}
