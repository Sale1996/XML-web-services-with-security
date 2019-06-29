import { Reservation } from './../../model/reservation.model';
import { UserService } from './../../services/user.service';
import { RatingService } from './../../services/rating.service';
import { ReservationService } from './../../services/reservation.service';
import { Location } from '@angular/common';
import { Message } from './../../model/message.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { MessageService } from 'src/app/services/message.service';
import { Rating } from 'src/app/model/rating.model';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/model/user.model';
import * as moment from 'moment';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  selected = 0;
  hovered = 0;
  readonly = false;
  messageObj: Message;
  reservations: Reservation[];
  reservation: Reservation;
  ratingFormGroup: FormGroup;
  sendMessageFormGroup: FormGroup;
  ratingObj: Rating = new Rating();
  userEmail: string;
  userLog: User;
  currTime: any;
  loc_reservation: number;
  resIdsend: number;

  resIdDelete: number;

  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private location: Location,
    private reservationService: ReservationService,
    private ratingService: RatingService,
    private authService: AuthService,
    private userService: UserService,

  ) { }

  ngOnInit() {

    this.ratingFormGroup = this.formBuilder.group({
      num: ['', Validators.required],
      comment: ['', Validators.required]
    });

    this.sendMessageFormGroup = this.formBuilder.group({
      message: ['']
    });

    this.userEmail = this.authService.getEmailFromToken(localStorage.getItem('access_token'));
    this.getCurUser();

    this.currTime = moment(new Date()).format();

  }

  getCurUser() {

    this.userService.getUserByEmail(this.userEmail).subscribe(userC => {

      this.userLog = userC;
      this.getReservations(this.userLog.id);

    });

  }

  getReservations(id: number): void {

    this.reservationService.getReservationByUserId(id).subscribe(reservation => this.reservations = reservation);

  }

   save() {
    this.prepareData();

    this.ratingService.createRating(this.ratingObj).subscribe((response) => {
      console.log('Response is: ', response);

   });

   }


  prepareData() {

    this.ratingObj.rating = this.ratingFormGroup.controls['num'].value;
    this.ratingObj.comment = this.ratingFormGroup.controls['comment'].value;
    this.ratingObj.accommodation_id = 2;
    this.ratingObj.reservation_id = this.resIdsend;
    this.ratingObj.verified = false;

  }

  cancelReservation() {

    this.reservationService.removeReservation(this.resIdDelete).subscribe(reservation =>{

      this.reservation = reservation;
      this.getReservations(this.userLog.id);

    });

  }
  saveClick(reservation: Reservation) {

    this.resIdDelete = reservation.reservationId;
  }
  sendMessage() {
    this.messageObj = {

      messageBody: this.sendMessageFormGroup.controls['message'].value,
      messageTime: moment(),
      userId: this.userLog.id,
      recieved: false,
      opened: false,
      reservationId: this.resIdsend
    }

    this.messageService.sendMessage(this.messageObj).subscribe((response) => {
      console.log('Response is: ', response);
      this.sendMessageFormGroup.reset();
   });

  }

  saveSend(reservation: Reservation) {

    this.resIdsend = reservation.reservationId;
  }


}
