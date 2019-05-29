import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UnitPricesModalComponent } from '../unit-prices-modal/unit-prices-modal.component';

@Component({
  selector: 'app-accommodation-unit-modal',
  templateUrl: './accommodation-unit-modal.component.html',
  styleUrls: ['./accommodation-unit-modal.component.css']
})
export class AccommodationUnitModalComponent implements OnInit {


  @Output() unit: EventEmitter<any> = new EventEmitter();
  unitForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder, private modalService: NgbModal) { }

  ngOnInit() {

    this.unitForm = this.formBuilder.group({
      id: [''],
      type: [''],
      category: [''],
      numberOfPeople: ['']

    });
  }

  onSubmit() {
    if (this.unitForm.valid) {
      this.unit.emit(this.unitForm.value); //as accommodationUnit
      this.activeModal.close();


    }
  }
}
