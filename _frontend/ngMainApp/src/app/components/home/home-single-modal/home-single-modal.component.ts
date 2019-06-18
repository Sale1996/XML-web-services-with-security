import { AccommodationUnit } from './../../../model/accommodation-unit.model';
import { Accommodation } from './../../../model/accommodation.model';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home-single-modal',
  templateUrl: './home-single-modal.component.html',
  styleUrls: ['./home-single-modal.component.css']
})
export class HomeSingleModalComponent implements OnInit {

  @Input() accommodation: Accommodation;
  @Output() answer: EventEmitter<AccommodationUnit> = new EventEmitter();

  @Input() accommodationUnit: AccommodationUnit;
  @Input() accommodationUnits: AccommodationUnit[];

  constructor(
    public activeModal: NgbActiveModal
  ) { }

  ngOnInit() {

  }

  onReserve(accommodationUnit: AccommodationUnit) {

      this.answer.emit(accommodationUnit);
      this.activeModal.close();

  }

}
