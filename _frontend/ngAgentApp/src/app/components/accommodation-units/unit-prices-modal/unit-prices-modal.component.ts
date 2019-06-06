import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-unit-prices-modal',
  templateUrl: './unit-prices-modal.component.html',
  styleUrls: ['./unit-prices-modal.component.css']
})
export class UnitPricesModalComponent implements OnInit {

  @Input() id: number;
  @Output() price: EventEmitter<any> = new EventEmitter();
  priceForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.priceForm = this.formBuilder.group({
      id: [''],
      dateFrom: [''],
      dateTo: [''],
      unitPrice: ['']

    });
  }

  onSubmit() {
    if (this.priceForm.valid) {

    }
  }

  closeModal() {
    this.price.emit(this.priceForm.value); //as accommodationUnit
    this.activeModal.close();
  }

}
