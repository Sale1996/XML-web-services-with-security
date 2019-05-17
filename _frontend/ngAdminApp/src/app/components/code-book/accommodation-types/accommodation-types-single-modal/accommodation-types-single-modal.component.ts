import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-accommodation-types-single-modal',
  templateUrl: './accommodation-types-single-modal.component.html',
  styleUrls: ['./accommodation-types-single-modal.component.css']
})
export class AccommodationTypesSingleModalComponent implements OnInit {

  @Input() id?: number;
  submitBtnText: string;
  accommodationTypeForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder) {}

  ngOnInit() {

    this.accommodationTypeForm = this.formBuilder.group({
      typeId: [''],
      typeName: ['', Validators.required]
    });

    if (this.id) {
      this.submitBtnText = 'Save Changes';
      this.getAccommodationTypeById(this.id);
    } else {
      this.submitBtnText = 'Add Type';
    }
  }

  getAccommodationTypeById(id: number) {
    this.accommodationTypeForm.patchValue({typeId: 1, typeName: 'Demo'});
  }

}
