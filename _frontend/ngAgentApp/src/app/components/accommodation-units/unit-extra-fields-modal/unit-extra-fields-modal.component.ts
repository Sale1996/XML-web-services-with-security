import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

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

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder) { }

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

  }

  /*
  getAdditionalServiceById(id: number) {
    this.additionalService.getAdditionalServiceById(id).subscribe(
      (service: AccommodationService) => this.additionalServiceForm.patchValue(service)
    );
  }
*/
  onSubmit() {
    if (this.additionalServiceForm.valid) {
      this.service.emit(this.additionalServiceForm.value); //as ExtraField
      this.activeModal.close();
    }
  }

}
