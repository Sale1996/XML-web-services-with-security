import { City } from './../../model/city.model';
import { ReservationService } from './../../services/reservation.service';
import { Accommodation } from './../../model/accommodation.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import {AccommodationService } from './../../services/accommodation.service';
import { Location } from '@angular/common';
import { Reservation } from 'src/app/model/reservation.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  homeFormGroup: FormGroup;
  accommodations: Accommodation[];
  accommodation: Accommodation;
  where: String;
  checkin: Number;
  checkout: Number;
  guests: Number;
  rooms: Number;
  reservations: Reservation[];
  reservation: Reservation;
  reservationObj: Reservation;
  city: City;
  cities2: City[];

  cities = [
    {id: 1, name: 'Vilnius'},
    {id: 2, name: 'Kaunas'},
    {id: 3, name: 'Pavilnys', disabled: true},
    {id: 4, name: 'Pabradė'},
    {id: 5, name: 'Klaipėda'}
];

  constructor(
    private formBuilder: FormBuilder,
    private accommodationService: AccommodationService,
    private reservationService: ReservationService,
    private location: Location
  ) { }

  ngOnInit() {
    this.homeFormGroup = this.formBuilder.group({
      where: [''],
      checkin: [''],
      checkout: [''],
      guests: [''],
      rooms: ['']
    });
  }

  searchFlights(where: String, checkin: Number, checkout: Number, guests: Number) {

    this.accommodationService.searchAccommotions(where, checkin, checkout, guests).subscribe(
      accommodation => this.accommodations = accommodation
    );
  }

  prepareData(){
    this.where = this.homeFormGroup.controls['where'].value;
    this.checkin = this.homeFormGroup.controls['checkin'].value;
    this.checkout = this.homeFormGroup.controls['checkout'].value;
    this.guests = this.homeFormGroup.controls['guests'].value;
  }

  prepareDataReservation(){

    // moram da pokupim na koju je akomodaciju kliknu i da uzmem sve njene podatke
    this.reservationObj.dateFrom = this.homeFormGroup.controls['checkin'].value;
    this.reservationObj.dateTo = this.homeFormGroup.controls['checkout'].value;
    // this.reservationObj.accommodationUnit_id = na koju je kliknuo
    // this.reservationObj.client_id = ulogovan korisnik
    // this.reservationObj.finalPrice = posebna klasa Price
    // this.reservationObj.confirmation = false;
    // this.reservationObj.id = id


  }

  onSubmit() {
    this.prepareData();
    this.searchFlights(this.where, this.checkin, this.checkout, this.guests);
  }

  reserve() {

    this.reservationService.reserve(this.reservationObj).subscribe((response) => {
      console.log('Response is: ', response);
      this.location.back();
   },
   (error) => {
      // catch the error
      console.error('An error occurred, ', error);
   });
  }

  getCities(): void {
    this.accommodationService.getCities().subscribe(city => this.cities2 = city);
}


}
