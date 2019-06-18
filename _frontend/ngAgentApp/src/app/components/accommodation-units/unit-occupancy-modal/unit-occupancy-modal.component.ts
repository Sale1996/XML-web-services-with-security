import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-unit-occupancy-modal',
  templateUrl: './unit-occupancy-modal.component.html',
  styleUrls: ['./unit-occupancy-modal.component.css']
})
export class UnitOccupancyModalComponent implements OnInit {

  @Input() unitId: number;
  @Output() occupancy: EventEmitter<any> = new EventEmitter();
  occupancyForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.occupancyForm = this.formBuilder.group({
      id: [''],
      dateFrom: [''],
      dateTo: [''],
      unitPrice: ['']

    });
  }

  onSubmit() {
    if (this.occupancyForm.valid) {
      this.occupancy.emit(this.occupancyForm.value); //as accommodationUnit
      this.activeModal.close();
      //ovde otvaramo novi modul...
    }
  }

}
