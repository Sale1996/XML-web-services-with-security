import { Component, OnInit } from '@angular/core';
import { Reservation } from 'src/app/model/reservation.model';
import { Observable } from 'rxjs';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  reservations$: Observable<Reservation[]>;

  constructor(private reservationService: ReservationService) {

  }

  ngOnInit() {
    this.getReservations();
  }


  getReservations() {
    this.reservations$ = this.reservationService.getReservations();
  }


  confirmReservation(id) {
    this.reservationService.confirmReservation(id).subscribe(
      () => {
        this.getReservations();
      }
    );
  }

}
