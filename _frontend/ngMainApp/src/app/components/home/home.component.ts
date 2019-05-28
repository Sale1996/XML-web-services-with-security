import { HomeSingleModalComponent } from './home-single-modal/home-single-modal.component';
import { City } from './../../model/city.model';
import { ReservationService } from './../../services/reservation.service';
import { Accommodation } from './../../model/accommodation.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import {AccommodationService } from './../../services/accommodation.service';
import { Location } from '@angular/common';
import { Reservation } from 'src/app/model/reservation.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  homeFormGroup: FormGroup;
  accommodations: Accommodation[];
  accommodation: Accommodation;
  where: Number;
  checkin: Number;
  checkout: Number;
  guests: Number;
  rooms: Number;
  reservations: Reservation[];
  reservation: Reservation;
  reservationObj: Reservation = new Reservation();
  city: City;
  cities: City[];
  local_accomm: Accommodation;

  constructor(
    private formBuilder: FormBuilder,
    private accommodationService: AccommodationService,
    private reservationService: ReservationService,
    private location: Location,
    private modalService: NgbModal
  ) { }

  ngOnInit() {
    this.homeFormGroup = this.formBuilder.group({
      where: [''],
      checkin: [''],
      checkout: [''],
      guests: ['']
    });

    this.getCities();
  }

  searchFlights(where: Number, checkin: Number, checkout: Number, guests: Number) {

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

    // bice izmenaa

    console.log('EEEEEEEEEEEEEEEEEEEEEEEE:  ', this.local_accomm);

    // this.reservationObj.dateFrom = this.homeFormGroup.controls['checkin'].value; 2019-06-28T18:44:27.534
    // this.reservationObj.dateTo = this.homeFormGroup.controls['checkout'].value;
    this.reservationObj.client = 1; // ulogovan korisnik
    this.reservationObj.accommodationUnit = this.local_accomm.accommodationId;
    this.reservationObj.confirmation = false;
    this.reservationObj.finalPrice = 100; // cena puta dani
    this.reservationObj.dateFrom = 'sta';
    this.reservationObj.dateTo = 'staa';

  }

  onSubmit() {
    this.prepareData();
    this.searchFlights(this.where, this.checkin, this.checkout, this.guests);
  }

  reserve() {

    this.prepareDataReservation();

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
    this.accommodationService.getCities().subscribe(city => this.cities = city);
  }

  openAccommodationModalSeeAvailability(a: Accommodation): void {
    const accommodationModalRef = this.modalService.open(HomeSingleModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });

      this.local_accomm = a;
      accommodationModalRef.componentInstance.accommodation = a;

      accommodationModalRef.componentInstance.answer.subscribe(
        (answer: string) => {

            if(answer === 'reserve') {

              this.reserve();
            }

        }
      );
  }

}
