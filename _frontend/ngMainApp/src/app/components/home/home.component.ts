import { Accommodation } from './../../model/accommodation.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import {AccommodationService } from './../../services/accommodation.service';

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

  constructor(
    private formBuilder: FormBuilder,
    private accommodationService: AccommodationService
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

  searchFlights(where: String, checkin: Number, checkout: Number, guests: Number, rooms: Number) {

    this.accommodationService.searchAccommotions(where, checkin, checkout, guests, rooms).subscribe(
      accommodation => this.accommodations = accommodation,
    );
  }

  prepareData(){
    this.where = this.homeFormGroup.controls['where'].value;
    this.checkin = this.homeFormGroup.controls['checkin'].value;
    this.checkout = this.homeFormGroup.controls['checkout'].value;
    this.guests = this.homeFormGroup.controls['guests'].value;
    this.rooms = this.homeFormGroup.controls['rooms'].value;
  }

  onSubmit() {
    this.prepareData();
    this.searchFlights(this.where, this.checkin, this.checkout, this.guests, this.rooms);
  }



}
