import { AccommodationUnit } from './../../model/accommodation-unit.model';
import { Accommodation } from './../../model/accommodation.model';
import { AccommodationUnitService } from './../../services/accommodation-unit.service';
import { UserService } from './../../services/user.service';
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
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/model/user.model';
import { Search } from 'src/app/model/search.model';

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
  userEmail: string;
  userLog: User;

  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private location: Location,
    private reservationService: ReservationService,
    private ratingService: RatingService,
    private authService: AuthService,
    private userService: UserService

  ) { }

  ngOnInit() {

    this.ratingFormGroup = this.formBuilder.group({
      num: ['', Validators.required],
      comment: ['', Validators.required]
    });

    this.userEmail = this.authService.getEmailFromToken(localStorage.getItem('access_token'));
    this.getCurUser();
    this.getReservations(this.userLog.id);

  }

  getCurUser() {

    this.userService.getUserByEmail(this.userEmail).subscribe(userC => this.userLog = userC);

  }

  getReservations(id: number): void {
    this.reservationService.getReservationByUserId(id).subscribe(reservation => this.reservations = reservation);

  }

  onSubmit() {

    this.prepareData();

    console.log(this.ratingObj);

    this.ratingService.createRating(this.ratingObj).subscribe((response) => {
      console.log('Response is: ', response);
      this.location.back();
   });

  }
  prepareData() {

    this.ratingObj.id = -1;
    this.ratingObj.rating = this.ratingFormGroup.controls['num'].value;
    this.ratingObj.comment = this.ratingFormGroup.controls['comment'].value;
    this.ratingObj.accommodation_id = 1;
    this.ratingObj.reservation_id = 2;
    this.ratingObj.verified = false;

  }


}
