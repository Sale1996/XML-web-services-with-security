import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ExtraFieldService } from 'src/app/services/extra-field.service';
import { ExtraField } from 'src/app/model/extra-field.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-unit-extra-fields-modal',
  templateUrl: './unit-extra-fields-modal.component.html',
  styleUrls: ['./unit-extra-fields-modal.component.css']
})
export class UnitExtraFieldsModalComponent implements OnInit {

  @Input() id?: number;
  @Output() service: EventEmitter<any> = new EventEmitter();
  submitBtnText: string;
  additionalServiceForm: FormGroup;
  unitExtraFields$: Observable<ExtraField[]>;
  extraFields$: Observable<ExtraField[]>;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder, private extraFieldService: ExtraFieldService) { }

  ngOnInit() {

    this.additionalServiceForm = this.formBuilder.group({
      extraFieldId: ['']
    });


    if (this.id) {
      this.submitBtnText = 'Save Changes';
      // this.getAdditionalServiceById(this.id);
    } else {
      this.submitBtnText = 'Add Service';
    }

    this.getAllExtraFieldsOfAccommodationUnit();
    this.getAllExtraFields();

  }


  getAllExtraFieldsOfAccommodationUnit() {
    this.unitExtraFields$ = this.extraFieldService.getExtraFieldsByUnit(this.id);
  }

  getAllExtraFields() {
    this.extraFields$ = this.extraFieldService.getExtraFields();
  }

  onSubmit() {
    if (this.additionalServiceForm.valid) {
      //dodaj....
    }
  }

  closeWindow() {
    this.service.emit(this.additionalServiceForm.value); //as ExtraField
    this.activeModal.close();
  }

}
