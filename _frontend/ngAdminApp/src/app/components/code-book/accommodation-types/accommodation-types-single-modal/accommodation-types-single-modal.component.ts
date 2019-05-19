import { Subject } from 'rxjs';
import { AccommodationType } from './../../../../model/accommodation-type.model';
import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { TypeService } from 'src/app/services/type.service';

@Component({
  selector: 'app-accommodation-types-single-modal',
  templateUrl: './accommodation-types-single-modal.component.html',
  styleUrls: ['./accommodation-types-single-modal.component.css']
})
export class AccommodationTypesSingleModalComponent implements OnInit {

  @Input() id?: number;
  @Output() type: EventEmitter<any> = new EventEmitter();
  submitBtnText: string;
  accommodationTypeForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder, private typeService: TypeService) {}

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
    this.typeService.getTypeById(id).subscribe(
      (type: AccommodationType) => this.accommodationTypeForm.patchValue(type)
    );
  }

  onSubmit() {
    if (this.accommodationTypeForm.valid) {
      this.type.emit(this.accommodationTypeForm.value as AccommodationType);
      this.activeModal.close();
    }
  }

}
