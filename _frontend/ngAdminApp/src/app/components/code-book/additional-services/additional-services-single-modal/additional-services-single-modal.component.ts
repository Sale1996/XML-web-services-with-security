import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AccommodationService } from 'src/app/model/accommodation-service.model';
import { AdditionalService } from 'src/app/services/additional.service';

@Component({
  selector: 'app-additional-services-single-modal',
  templateUrl: './additional-services-single-modal.component.html',
  styleUrls: ['./additional-services-single-modal.component.css']
})
export class AdditionalServicesSingleModalComponent implements OnInit {

  @Input() id?: number;
  @Output() service: EventEmitter<any> = new EventEmitter();
  submitBtnText: string;
  additionalServiceForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder, private additionalService: AdditionalService) {}

  ngOnInit() {

    this.additionalServiceForm = this.formBuilder.group({
      extraFieldId: [''],
      extraFieldName: ['', Validators.required],
      extraPrice: ['', Validators.required],
      optional: [true]
    });

    if (this.id) {
      this.submitBtnText = 'Save Changes';
      this.getAdditionalServiceById(this.id);
    } else {
      this.submitBtnText = 'Add Service';
    }
  }

  getAdditionalServiceById(id: number) {
    this.additionalService.getAdditionalServiceById(id).subscribe(
      (service: AccommodationService) => this.additionalServiceForm.patchValue(service)
    );
  }

  onSubmit() {
    if (this.additionalServiceForm.valid) {
      this.service.emit(this.additionalServiceForm.value as AccommodationService);
      this.activeModal.close();
    }
  }

}
