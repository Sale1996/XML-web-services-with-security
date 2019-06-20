import { Component, EventEmitter, OnInit, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { CityService } from 'src/app/services/city.service';
import { City } from 'src/app/model/city.model';


@Component({
  selector: 'app-cities-single-modal',
  templateUrl: './cities-single-modal.component.html',
  styleUrls: ['./cities-single-modal.component.css']
})
export class CitiesSingleModalComponent implements OnInit {

  @Input() id?: number;
  @Output() service: EventEmitter<any> = new EventEmitter();
  submitBtnText: string;
  cityForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder, private cityService: CityService) { }

  ngOnInit() {

    this.cityForm = this.formBuilder.group({
      cityId: [''],
      name: ['', Validators.required],
      xCord: ['', Validators.required],
      yCord: ['', Validators.required]
    });

    if (this.id) {
      this.submitBtnText = 'Save Changes';
      this.getCityById(this.id);
    } else {
      this.submitBtnText = 'Add City';
    }

  }

  getCityById(id: number) {
    this.cityService.getCityById(id).subscribe(
      (city: City) => this.cityForm.patchValue(city)
    );
  }

  onSubmit() {
    if (this.cityForm.valid) {
      this.service.emit(this.cityForm.value as City);
      this.activeModal.close();
    }
  }

}
