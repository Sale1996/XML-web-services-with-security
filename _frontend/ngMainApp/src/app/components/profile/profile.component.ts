import { ReservationService } from './../../services/reservation.service';
import { Location } from '@angular/common';
import { Message } from './../../model/message.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { MessageService } from 'src/app/services/message.service';
import { Reservation } from 'src/app/model/reservation.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  selected = 0;
  hovered = 0;
  readonly = false;
  messageForm: FormGroup;
  messageObj: Message = new Message();
  reservations: Reservation[];
  reservation: Reservation;

  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private location: Location,
    private reservationService: ReservationService
  ) { }

  ngOnInit() {

    this.getReservations();

  }

  getReservations(): void {
    this.reservationService.getReservation().subscribe(reservation => this.reservations = reservation);
  }





}
