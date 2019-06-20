import { AccommodationUnitService } from './../../../services/accommodation-unit.service';
import { AccommodationUnitsWith } from './../../../model/accommodation-units-with.model';
import { AccommodationService } from './../../../services/accommodation.service';
import { AccommodationUnit } from './../../../model/accommodation-unit.model';
import { Accommodation } from './../../../model/accommodation.model';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Search } from 'src/app/model/search.model';

@Component({
  selector: 'app-home-single-modal',
  templateUrl: './home-single-modal.component.html',
  styleUrls: ['./home-single-modal.component.css']
})
export class HomeSingleModalComponent implements OnInit {

  @Input() accommodation: Accommodation;
  @Input() searchObj: Search;

  @Output() answer: EventEmitter<AccommodationUnit> = new EventEmitter();

  accommodationUnitsWith: AccommodationUnitsWith;
  accommodationUnitList: AccommodationUnit[];
  prices: Number[];


  //@Input() accommodationUnitsWith: AccommodationUnitsWith;
  //@Input() accommodationUnits: AccommodationUnit[];

  constructor(
    public activeModal: NgbActiveModal,
    private accommodationService: AccommodationService,
    private accommodationUnitService: AccommodationUnitService,
  ) { }

  ngOnInit() {

    this.getAccommodations();
  }


  getAccommodations() {

    this.accommodationUnitService.getAccommotionUnits(this.accommodation.accommodationId, this.searchObj).subscribe(accommodationUnitsWith => {
      console.log('dada ');
      this.accommodationUnitsWith = accommodationUnitsWith;

      this.accommodationUnitList = this.accommodationUnitsWith.units;
      this.prices = this.accommodationUnitsWith.prices;

    });
  }

  onReserve(accommodationUnit: AccommodationUnit) {

      this.answer.emit(accommodationUnit);
      this.activeModal.close();

  }

}
