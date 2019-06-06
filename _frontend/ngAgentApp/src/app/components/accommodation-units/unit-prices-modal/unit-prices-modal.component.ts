import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Price } from 'src/app/model/price.model';
import { Observable } from 'rxjs';
import { PriceService } from 'src/app/services/price.service';

@Component({
  selector: 'app-unit-prices-modal',
  templateUrl: './unit-prices-modal.component.html',
  styleUrls: ['./unit-prices-modal.component.css']
})
export class UnitPricesModalComponent implements OnInit {

  @Input() unitId: number;
  @Output() price: EventEmitter<any> = new EventEmitter();
  priceForm: FormGroup;
  prices$: Observable<Price[]>;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder, private priceService: PriceService) { }

  ngOnInit() {

    this.priceForm = this.formBuilder.group({
      id: [''],
      dateFrom: [''],
      dateTo: [''],
      unitPrice: ['']

    });


  }

  getAllPricesOfAccommodationUnit() {
    this.prices$ = this.priceService.getPrices(this.unitId);
  }

  deletePriceFromAccommodationUnit(priceIdd: number) {
    this.priceService.deletePrice(priceIdd).subscribe((data) => {
      this.getAllPricesOfAccommodationUnit();
    });
  }

  createPriceOfAccommodationUnit() {
    this.priceService.createPrice(this.priceForm.value as Price).subscribe((data) => {
      this.getAllPricesOfAccommodationUnit();
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
