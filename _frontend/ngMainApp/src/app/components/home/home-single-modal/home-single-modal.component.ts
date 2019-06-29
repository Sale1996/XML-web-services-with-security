import { Picture } from './../../../model/picture.model';
import { AccommodationUnitService } from './../../../services/accommodation-unit.service';
import { AccommodationUnitsWith } from './../../../model/accommodation-units-with.model';
import { AccommodationService } from './../../../services/accommodation.service';
import { AccommodationUnit } from './../../../model/accommodation-unit.model';
import { Accommodation } from './../../../model/accommodation.model';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Search } from 'src/app/model/search.model';
import { PictureService } from 'src/app/services/picture.service';

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
  accomodationUnit: AccommodationUnit;
  accommodationUnitList: AccommodationUnit[];
  prices: Number[];
  pictures: Picture[];

  constructor(
    public activeModal: NgbActiveModal,
    private accommodationService: AccommodationService,
    private accommodationUnitService: AccommodationUnitService,
    private pictureService: PictureService
  ) { }

  ngOnInit() {

    this.getAccommodations();
  }


  getAccommodations() {

    this.accommodationUnitService.getAccommotionUnits(this.accommodation.accommodationId, this.searchObj).subscribe(accommodationUnitsWith => {

      this.accommodationUnitsWith = accommodationUnitsWith;

      this.accommodationUnitList = this.accommodationUnitsWith.units;
      this.prices = this.accommodationUnitsWith.prices;
      this.getPictures(this.accommodation.accommodationId);

    });
  }

  onReserve(accommodationUnit: AccommodationUnit) {

      this.answer.emit(accommodationUnit);
      this.activeModal.close();

  }

  sort(par: number) {

    this.accommodationService.sort(this.accommodationUnitsWith, par).subscribe(
      accomodationUnit => {

        this.accommodationUnitsWith = accomodationUnit;

        this.accommodationUnitList = this.accommodationUnitsWith.units;
        this.prices = this.accommodationUnitsWith.prices;

      }

    );

  }

  isLoggedIn(): boolean {
    if (localStorage.hasOwnProperty('access_token')) {
      return true;
    } else {
      return false;
    }
  }

  getPictures(id: number): void {
    this.pictureService.getPictures(id).subscribe(picture => this.pictures = picture);
  }



}
