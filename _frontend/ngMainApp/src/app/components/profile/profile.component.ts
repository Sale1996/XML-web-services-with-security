import { RatingService } from './../../services/rating.service';
import { ReservationService } from './../../services/reservation.service';
import { Location } from '@angular/common';
import { Message } from './../../model/message.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { MessageService } from 'src/app/services/message.service';
import { Reservation } from 'src/app/model/reservation.model';
import { Observable } from 'rxjs';
import { Rating } from 'src/app/model/rating.model';

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
  ratingFormGroup: FormGroup;
  ratingObj: Rating = new Rating();

  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private location: Location,
    private reservationService: ReservationService,
    private ratingService: RatingService

  ) { }

  ngOnInit() {

    this.ratingFormGroup = this.formBuilder.group({
      num: ['', Validators.required],
      comment: ['', Validators.required]
    });

    this.getReservations();

  }

  getReservations(): void {
    this.reservationService.getReservation().subscribe(reservation => this.reservations = reservation);
  }

  onSubmit() {

    this.prepareData();

    this.ratingService.createRating(this.ratingObj).subscribe((response) => {
      console.log('Response is: ', response);
      this.location.back();
   },
   (error) => {
      // catch the error
      console.error('An error occurred, ', error);
   });
  }
  prepareData() {

    this.ratingObj.rating = this.ratingFormGroup.controls['num'].value;
    this.ratingObj.comment = this.ratingFormGroup.controls['comment'].value;
    this.ratingObj.accommodation_id = 1;
    this.ratingObj.reservation_id = 2;
    this.ratingObj.verified = false;
    this.ratingObj.lastUpdated = null;

  }


}
