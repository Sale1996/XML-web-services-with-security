import { ExtraFieldService } from './../../services/extra-field.service';
import { CategoryService } from './../../services/category.service';
import { TypeService } from './../../services/type.service';
import { Category } from './../../model/category.model';
import { ExtraField } from './../../model/extra-field.model';
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
import {NgbDateAdapter, NgbDateStruct, NgbDateNativeAdapter} from '@ng-bootstrap/ng-bootstrap';
import * as moment from 'moment';
import { Search } from 'src/app/model/search.model';
import { Type } from 'src/app/model/type.model';

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
  checkin: String;
  checkout: String;
  guests: Number;
  rooms: Number;
  reservations: Reservation[];
  reservation: Reservation;
  reservationObj: Reservation = new Reservation();
  city: City;
  cities: City[];
  local_accomm: Accommodation;
  searchShow: boolean;
  accommodationShow: boolean;
  model1: Date;

  // search modal
  searchObj: Search;
  extraFiled: ExtraField;
  extraFileds: ExtraField[];
  type: Type;
  types: Type[];
  category: Category;
  categories: Category[];



  constructor(
    private formBuilder: FormBuilder,
    private accommodationService: AccommodationService,
    private reservationService: ReservationService,
    private typeService: TypeService,
    private categoryService: CategoryService,
    private extraFieldService: ExtraFieldService,
    private location: Location,
    private modalService: NgbModal
  ) { }

  ngOnInit() {
    this.homeFormGroup = this.formBuilder.group({
      where: ['', Validators.required],
      checkin: ['', Validators.required],
      checkout: ['', Validators.required],
      guests: ['', Validators.required]
    });

    this.searchShow = true;
    this.accommodationShow = false;
    this.getCities();
  }

  getExtraFields(): void {
    this.typeService.getTypes().subscribe(type => this.types = type);
  }

  getCategories(): void  {
    this.categoryService.getCategories().subscribe(category => this.categories = category);
  }

  getTypes(): void  {
    this.extraFieldService.getExtraFields().subscribe(extraFiled => this.extraFileds = extraFiled);
  }

  searchAcommodations(where: Number, checkin: String, checkout: String, guests: Number) {

    // this.searchObj.category
    // this.searchObj.distance
    // this.searchObj.type
    // this.searchObj.extraFields

    this.accommodationService.searchAccommotions(where, checkin, checkout, guests, this.searchObj).subscribe(
      accommodation => this.accommodations = accommodation
    );

  }

  prepareData(){
    this.where = this.homeFormGroup.controls['where'].value;
    this.checkin = moment(this.homeFormGroup.controls['checkin'].value).toISOString();
    this.checkin = this.checkin.substring(0, this.checkin.length - 1);

    this.checkout = moment(this.homeFormGroup.controls['checkout'].value).toISOString();
    this.checkout = this.checkout.substring(0, this.checkout.length - 1);

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
    this.reservationObj.dateFrom = '2019-05-28T20:29:44';
    this.reservationObj.dateTo = '2019-05-28T20:29:44';

  }

  onSubmit() {
    this.prepareData();
    this.searchAcommodations(this.where, this.checkin, this.checkout, this.guests);
    this.searchShow = false;
    this.accommodationShow = true;

  }

  // problem
  dataFilter() {

    this.getExtraFields();
    this.getCategories();
    this.getTypes();
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
