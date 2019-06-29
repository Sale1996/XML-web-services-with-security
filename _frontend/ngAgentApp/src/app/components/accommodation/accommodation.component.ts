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
import { AgentService } from 'src/app/services/agent.service';
import { AuthService } from 'src/app/services/auth.service';
import { Agent } from 'src/app/model/agent.model';

@Component({
  selector: 'app-accommodation',
  templateUrl: './accommodation.component.html',
  styleUrls: ['./accommodation.component.css']
})
export class AccommodationComponent implements OnInit {

  userEmail: string;
  userId: number;
  accommodationForm: FormGroup;
  submitBtnText: string = "blabla";
  accommodationName: string;
  newAccommodation: Accommodation;
  cities$: Observable<City[]>;
  pictures$: Observable<Picture[]>;
  imageUrl: string = "/assets/img/default_image.png";
  private base64textString: string = "";


  constructor(private formBuilder: FormBuilder,
    private accommodationService: AccommodationService,
    private cityService: CityService,
    private pictureService: PictureService,
    private domSanitizer: DomSanitizer,
    private authService: AuthService,
    private agentService: AgentService
  ) { }

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
    let binaryString = readerEvt.target.result;
    this.base64textString = btoa(binaryString);
    //ovde pravimo novu sliku...
    let pictureAccommodation = (this.accommodationForm.value as Accommodation);
    pictureAccommodation.accommodationId = +localStorage.getItem('accommodation');
    let picture: Picture = {
      pictureId: -1,
      picUrl: this.base64textString,
      accommodation: pictureAccommodation
    };
    if (pictureAccommodation.accommodationId) {
      this.pictureService.createPicture(picture).subscribe(() => {
        this.getPictures(parseInt(localStorage.getItem('accommodationId')));
      });
    }

  }

  ngOnInit() {

    this.userEmail = this.authService.getEmailFromToken(localStorage.getItem('access_token'));
    this.getAgentId();

    this.accommodationForm = this.formBuilder.group({
      accommodationName: ['', Validators.required],
      numberOfDaysBeforeCancelation: ['', Validators.required],
      description: [''],
      city: this.formBuilder.group({
        cityId: [''],
        name: [''],
        xCord: [''],
        yCord: ['']
      }),
      agentId: [''],
      accommodationId: [-1],
    });

    this.getCities();
    if (localStorage.getItem('accommodation')) {
      this.submitBtnText = 'Save Changes';
      // this.getAccommodationServiceById(localStorage.getItem('agent'));
    } else {
      this.submitBtnText = 'Add Accommodation';
      this.accommodationName = '';
    }
  }

  getAgentId() {
    const id = this.agentService.getAgentByEmail(this.userEmail).subscribe(
      response => {
        this.userId = (response as Agent).id;
        localStorage.setItem('agent', this.userId.toString());
        this.getAccommodationServiceById(this.userId.toString());
      }
    );
  }

  getCities() {
    this.cities$ = this.cityService.getCities();
  }

  getPictures(id: number) {
    this.pictures$ = this.pictureService.getPicture(id);
  }

  deletePicture(id: number) {
    this.pictureService.deletePicture(id).subscribe(() => {
      this.getPictures(parseInt(localStorage.getItem('accommodationId')));
    });
  }

  getAccommodationServiceById(id) {
    this.accommodationService.getAccommodationById(id).subscribe(
      (accommodation: Accommodation) => {
        this.accommodationForm.patchValue(accommodation);
        this.accommodationName = accommodation.accommodationName;
        localStorage.setItem('accommodationId', accommodation.accommodationId.toString());
        this.getPictures(accommodation.accommodationId);
      }
    );
  }


  onSubmit() {
    if (this.accommodationForm.valid) {
      let createdAccommodation: Accommodation = (this.accommodationForm.value as Accommodation);
      createdAccommodation.agentId = parseInt(localStorage.getItem('agent'));
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
