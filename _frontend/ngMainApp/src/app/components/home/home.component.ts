import { AccommodationUnitsWith } from './../../model/accommodation-units-with.model';
import { Picture } from './../../model/picture.model';
import { PictureService } from './../../services/picture.service';
import { AuthService } from './../../services/auth.service';
import { User } from './../../model/user.model';
import { AccommodationUnitService } from './../../services/accommodation-unit.service';
import { AccommodationUnit } from './../../model/accommodation-unit.model';
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
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  homeFormGroup: FormGroup;
  filterByFormGroup: FormGroup;
  accommodations: Accommodation[];
  accommodation: Accommodation;
  where: Number;
  checkin: string;
  checkout: string;
  guests: Number;
  rooms: Number;
  reservations: Reservation[];
  reservation: Reservation;
  reservationObj: Reservation;
  city: City;
  cities: City[];
  local_accomm: Accommodation;
  searchShow: boolean;
  accommodationShow: boolean;
  model1: Date;

  // search modal
  searchObj: Search = new Search();
  extraField: ExtraField;
  extraFields: ExtraField[];
  type: Type;
  types: Type[];
  category: Category;
  categories: Category[];

  picture: Picture;
  pictures: Picture[];

  extraFieldsNew: number[];

  accommodationUnit: AccommodationUnit;
  accommodationUnits: AccommodationUnit[];

  accommodationUnitsWith: AccommodationUnitsWith;

  userEmail: string;
  userLog: User;

  accommodationUnitList: AccommodationUnit[];
  prices: number[];



  constructor(
    private formBuilder: FormBuilder,
    private accommodationService: AccommodationService,
    private reservationService: ReservationService,
    private typeService: TypeService,
    private categoryService: CategoryService,
    private extraFieldService: ExtraFieldService,
    private location: Location,
    private modalService: NgbModal,
    private accommodationUnitService: AccommodationUnitService,
    private authService: AuthService,
    private userService: UserService,
    private pictureService: PictureService
  ) { }

  ngOnInit() {
    this.homeFormGroup = this.formBuilder.group({
      where: ['', Validators.required],
      checkin: ['', Validators.required],
      checkout: ['', Validators.required],
      guests: ['', Validators.required]
    });

    this.filterByFormGroup = this.formBuilder.group({
      exampleRadios: [null], // distance
      exampleRadiosC: [null], // category
      exampleRadiosT: [null], // type
      // exampleRadiosE: [] // extra
    });

    this.userEmail = this.authService.getEmailFromToken(localStorage.getItem('access_token'));
    this.getCurUser();

    this.searchShow = true;
    this.accommodationShow = false;
    this.getCities();
    this.extraFieldsNew = [];
  }


  getCurUser() {

    this.userService.getUserByEmail(this.userEmail).subscribe(userC => this.userLog = userC);

  }

  save(){

    if (this.filterByFormGroup.controls['exampleRadios'].value == null) {

      this.searchObj.distance = -1;

    } else {

      this.searchObj.distance = this.filterByFormGroup.controls['exampleRadios'].value;

    }

    if (this.filterByFormGroup.controls['exampleRadiosC'].value == null) {

      this.searchObj.category = -1;

    } else {

      this.searchObj.category = this.filterByFormGroup.controls['exampleRadiosC'].value;

    }

    if (this.filterByFormGroup.controls['exampleRadiosT'].value == null) {

      this.searchObj.type = -1;

    } else {

      this.searchObj.type = this.filterByFormGroup.controls['exampleRadiosT'].value;

    }

    if (this.extraFieldsNew.length > 0) {

      this.searchObj.extraFields = this.extraFieldsNew;

    } else {

      this.searchObj.extraFields = [];

    }

    console.log(this.searchObj);


    this.accommodationService.searchAccommotions(this.where, this.guests, this.searchObj).subscribe(
      accommodation => {
        this.accommodations = accommodation;
      });
  }

  getAccommodations(id: number, search: Search, accommodationModalRef) {

    console.log('usaaaaaaao ');

    return this.accommodationUnitService.getAccommotionUnits(id, search).subscribe(accommodationUnitsWith => {

      this.accommodationUnitsWith = accommodationUnitsWith;

      accommodationModalRef.componentInstance.accommodationUnitsWith = this.accommodationUnitsWith;


      this.accommodationUnitList = this.accommodationUnitsWith.units;
      this.prices = this.accommodationUnitsWith.prices;

      console.log('ovo: ' + this.accommodationUnitList);
      console.log('ovo2: ' + this.prices);

    });
  }

  getPictures(accommodationId: number) {
    this.pictureService.getPictures(accommodationId).subscribe(picture => this.pictures = picture);
  }

  getExtraFields(): void {
    this.typeService.getTypes().subscribe(type => this.types = type);
  }

  getCategories(): void  {
    this.categoryService.getCategories().subscribe(category => this.categories = category);
  }

  getTypes(): void  {
    this.extraFieldService.getExtraFields().subscribe(extraFiled => this.extraFields = extraFiled);
  }


  searchAcommodations(where: Number, checkin: String, checkout: String, guests: Number) {

    this.searchObj.dateFrom =  this.checkin;
    this.searchObj.dateTo = this.checkout;
    this.searchObj.category = -1;
    this.searchObj.distance = -1;
    this.searchObj.type = -1;
    this.searchObj.extraFields = [];

    this.accommodationService.searchAccommotions(where, guests, this.searchObj).subscribe(
      accommodation => this.accommodations = accommodation
    );

  }

  prepareData(){
    this.where = this.homeFormGroup.controls['where'].value;

    this.checkout = moment(this.homeFormGroup.controls['checkout'].value).toISOString();
    this.checkout = this.checkout.substring(0, this.checkout.length - 1);

    this.checkin = moment(this.homeFormGroup.controls['checkin'].value).toISOString();
    this.checkin = this.checkin.substring(0, this.checkin.length - 1);

    this.guests = this.homeFormGroup.controls['guests'].value;
  }

  extraFieldFun(extra: ExtraField, event) {

    if (event.target.checked) {

      this.extraFieldsNew.push(extra.extraFieldId);

    } else {

      let index = this.extraFieldsNew.indexOf(extra.extraFieldId);

      if(index !== -1) {
        this.extraFieldsNew.splice(index, 1);
      }

    }

  }

  checkChecked(id: number) {

    if(this.extraFieldsNew.indexOf(id) !== -1) {
      return 1;
    }
    return 0;

  }

  onSubmit() {
    this.prepareData();
    this.searchAcommodations(this.where, this.checkin, this.checkout, this.guests);
    this.searchShow = false;
    this.accommodationShow = true;

  }

  dataFilter() {

    this.getExtraFields();
    this.getCategories();
    this.getTypes();

  }

  reserve(accommodationUnit: AccommodationUnit) {
    console.log(accommodationUnit.accomodation.accommodationId);
    this.reservationObj = {

      dateFrom: this.checkin,
      dateTo: this.checkout,
      finalPrice: 100,
      confirmation: false,
      accommodationUnit: accommodationUnit.accommodationUnitId,
      client: this.userLog.id,
      accommodation_id: accommodationUnit.accomodation.accommodationId

    }

    console.log(this.reservationObj);

    this.reservationService.reserve(this.reservationObj).subscribe((response) => {
      console.log('Response is: ', response);
      location.assign('/profile');
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
      accommodationModalRef.componentInstance.searchObj = this.searchObj;

      accommodationModalRef.componentInstance.answer.subscribe(
        (accommodationUnit: AccommodationUnit) => {

            if(accommodationUnit !== null) {

              this.reserve(accommodationUnit);
            }

        }
      );



  }

}
