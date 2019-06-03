import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AccommodationService } from 'src/app/services/accommodation.service';
import { Accommodation } from 'src/app/model/accommodation.model';
import { City } from 'src/app/model/city.model';
import { Observable } from 'rxjs';
import { CityService } from 'src/app/services/city.service';

@Component({
  selector: 'app-accommodation',
  templateUrl: './accommodation.component.html',
  styleUrls: ['./accommodation.component.css']
})
export class AccommodationComponent implements OnInit {

  accommodationForm: FormGroup;
  submitBtnText: string = "blabla";
  accommodationName: string;
  newAccommodation: Accommodation;
  cities$: Observable<City[]>;

  constructor(private formBuilder: FormBuilder, private accommodationService: AccommodationService, private cityService: CityService) { }


  ngOnInit() {

    this.accommodationForm = this.formBuilder.group({
      accommodationName: ['', Validators.required],
      numberOfDaysBeforeCancelation: ['', Validators.required],
      description: [''],
      city: ['', Validators.required],
    });


    this.getCities();

    if (localStorage.getItem('accommodation')) {
      this.submitBtnText = 'Save Changes';
      this.getAccommodationServiceById(localStorage.getItem('accommodation'));
    } else {
      this.submitBtnText = 'Add Accommodation';
      this.accommodationName = "";
    }
  }


  getCities() {
    this.cities$ = this.cityService.getCities();
  }

  getAccommodationServiceById(id) {
    this.accommodationService.getAccommodationById(id).subscribe(
      (accommodation: Accommodation) => {
        this.accommodationForm.patchValue(accommodation);
        this.accommodationName = accommodation.accommodationName;
      }
    );
  }


  onSubmit() {
    if (this.accommodationForm.valid) {
      var createdAccommodation = (this.accommodationForm.value as Accommodation);
      if (!localStorage.getItem('accommodation')) {

        createdAccommodation.accommodationId = -1;
        this.accommodationService.createAccommodation(createdAccommodation).subscribe(
          (accommodation: Accommodation) => {
            localStorage.setItem('accommodation', accommodation.accommodationId.toString());
            this.submitBtnText = 'Save Changes';
            this.accommodationName = accommodation.accommodationName;
          }
        );

      }
      else {
        createdAccommodation.accommodationId = parseInt(localStorage.getItem('accommodation'));
        this.accommodationService.updateAccommodation(createdAccommodation).subscribe(
          (accommodation: Accommodation) => {
            this.accommodationName = accommodation.accommodationName;
          }
        )
      }
    }
  }

}
