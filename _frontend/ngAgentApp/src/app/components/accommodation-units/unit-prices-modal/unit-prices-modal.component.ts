import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Price } from 'src/app/model/price.model';
import { Observable } from 'rxjs';
import { PriceService } from 'src/app/services/price.service';
import { AccommodationUnit } from 'src/app/model/accommodation-unit.model';

@Component({
  selector: 'app-unit-prices-modal',
  templateUrl: './unit-prices-modal.component.html',
  styleUrls: ['./unit-prices-modal.component.css']
})
export class UnitPricesModalComponent implements OnInit {

  @Input() creation?: boolean;
  @Input() unit: AccommodationUnit;
  @Output() price: EventEmitter<any> = new EventEmitter();
  priceForm: FormGroup;
  prices$: Observable<Price[]>;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder, private priceService: PriceService) { }

  ngOnInit() {

    this.priceForm = this.formBuilder.group({
      id: [-1],
      dateFrom: [''],
      dateTo: [''],
      amount: ['']

    });

    this.getAllPricesOfAccommodationUnit();

  }


  deletePrice(id: number) {
    this.priceService.deletePrice(id).subscribe(() => {
      this.getAllPricesOfAccommodationUnit();
    })
  }

  getAllPricesOfAccommodationUnit() {
    this.prices$ = this.priceService.getPrices(this.unit.accommodationUnitId);
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
      const newPrice = (this.priceForm.value as Price);
      newPrice.accommodationUnit = this.unit;
      this.priceService.createPrice(newPrice).subscribe((data) => {
        this.getAllPricesOfAccommodationUnit();
      });

    }
  }

  closeModal() {
    this.activeModal.close();
    if (this.creation) {
      this.price.emit();
    }
  }



}
