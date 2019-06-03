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


  // pagination properties
  currentPage = 1;
  collectionSize = 200;
  pageSize: number;
  pageSizes: number[] = [25, 50, 100];
  reservations$: Observable<Reservation[]>;



  constructor(private reservationService: ReservationService) {
    this.pageSize = this.pageSizes[0];

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
