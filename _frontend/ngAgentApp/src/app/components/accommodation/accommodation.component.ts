import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AccommodationService } from 'src/app/services/accommodation.service';
import { Accommodation } from 'src/app/model/accommodation.model';
import { City } from 'src/app/model/city.model';
import { Observable } from 'rxjs';
import { CityService } from 'src/app/services/city.service';
import { Picture } from 'src/app/model/picture.model';
import { PictureService } from 'src/app/services/picture.service';
import { DomSanitizer } from '@angular/platform-browser';

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
  pictures$: Observable<Picture[]>;
  imageUrl: string = "/assets/img/default_image.png";
  private base64textString: string = "";


  constructor(private formBuilder: FormBuilder, private accommodationService: AccommodationService, private cityService: CityService, private pictureService: PictureService, private domSanitizer: DomSanitizer) { }

  handleFileSelect(evt) {
    var files = evt.target.files;

    for (let i = 0; i < files.length; i++) {

      var file = files[i];

      if (files && file) {
        var reader = new FileReader();

        reader.onload = this._handleReaderLoaded.bind(this);

        reader.readAsBinaryString(file);
      }

    }

  }

  _handleReaderLoaded(readerEvt) {
    var binaryString = readerEvt.target.result;
    this.base64textString = btoa(binaryString);
    //ovde pravimo novu sliku...
    var pictureAccommodation = (this.accommodationForm.value as Accommodation);
    pictureAccommodation.accommodationId = +localStorage.getItem('accommodation');
    var picture: Picture = {
      pictureId: -1,
      picUrl: this.base64textString,
      accommodation: pictureAccommodation
    };
    if (pictureAccommodation.accommodationId) {
      this.pictureService.createPicture(picture).subscribe(() => {
        this.getPictures();
      });
    }

  }

  ngOnInit() {

    this.accommodationForm = this.formBuilder.group({
      accommodationName: ['', Validators.required],
      numberOfDaysBeforeCancelation: ['', Validators.required],
      description: [''],
      city: ['', Validators.required],
      agentId: [''],
      accommodationId: [-1],
    });


    this.getCities();
    this.getPictures();

    if (localStorage.getItem('accommodation')) {
      this.submitBtnText = 'Save Changes';
      this.getAccommodationServiceById(localStorage.getItem('accommodation'));
    } else {
      this.submitBtnText = 'Add Accommodation';
      this.accommodationName = '';
    }
  }


  getCities() {
    this.cities$ = this.cityService.getCities();
  }

  getPictures() {
    this.pictures$ = this.pictureService.getPicture();
  }

  deletePicture(id: number) {
    this.pictureService.deletePicture(id).subscribe(() => {
      this.getPictures();
    });
  }

  getAccommodationServiceById(id) {
    this.accommodationService.getAccommodationById(id).subscribe(
      (accommodation: Accommodation) => {
        this.accommodationForm.patchValue(accommodation);
        this.accommodationName = accommodation.accommodationName;
        localStorage.setItem('accommodationId', accommodation.accommodationId.toString());
      }
    );
  }


  onSubmit() {
    if (this.accommodationForm.valid) {
      var createdAccommodation = (this.accommodationForm.value as Accommodation);
      if (!localStorage.getItem('accommodation')) {
        this.accommodationService.createAccommodation(createdAccommodation).subscribe(
          (accommodation: Accommodation) => {
            localStorage.setItem('accommodation', accommodation.accommodationId.toString());
            this.submitBtnText = 'Save Changes';
            this.accommodationName = accommodation.accommodationName;
          }
        );

      }
      else {
        // createdAccommodation.accommodationId = parseInt(localStorage.getItem('accommodation'));
        this.accommodationService.updateAccommodation(createdAccommodation).subscribe(
          (accommodation: Accommodation) => {
            this.accommodationName = accommodation.accommodationName;
          }
        )
      }
    }
  }

}
