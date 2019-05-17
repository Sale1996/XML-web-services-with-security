import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-additional-services-single-modal',
  templateUrl: './additional-services-single-modal.component.html',
  styleUrls: ['./additional-services-single-modal.component.css']
})
export class AdditionalServicesSingleModalComponent implements OnInit {

  @Input() id?: number;
  submitBtnText: string;
  additionalServiceForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder) {}

  ngOnInit() {

    this.additionalServiceForm = this.formBuilder.group({
      serviceId: [''],
      serviceName: ['', Validators.required]
    });

    if (this.id) {
      this.submitBtnText = 'Save Changes';
      this.getAdditionalServiceById(this.id);
    } else {
      this.submitBtnText = 'Add Service';
    }
  }

  getAdditionalServiceById(id: number) {
    this.additionalServiceForm.patchValue({serviceId: 1, serviceName: 'Demo'});
  }

}
