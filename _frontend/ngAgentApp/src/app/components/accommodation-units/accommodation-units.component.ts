import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AccommodationUnitModalComponent } from './accommodation-unit-modal/accommodation-unit-modal.component';
import { UnitPricesModalComponent } from './unit-prices-modal/unit-prices-modal.component';
import { UnitExtraFieldsModalComponent } from './unit-extra-fields-modal/unit-extra-fields-modal.component';

@Component({
  selector: 'app-accommodation-units',
  templateUrl: './accommodation-units.component.html',
  styleUrls: ['./accommodation-units.component.css']
})
export class AccommodationUnitsComponent implements OnInit {

  // pagination properties
  currentPage = 1;
  collectionSize = 200;
  pageSize: number;
  pageSizes: number[] = [25, 50, 100];

  constructor(private modalService: NgbModal) {
    this.pageSize = this.pageSizes[0];

  }

  ngOnInit() {
  }



  openNewUnitModal() {
    const newUnitModalRef = this.modalService.open(AccommodationUnitModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    newUnitModalRef.componentInstance.unit.subscribe(
      () => {
        const newPriceModal = this.modalService.open(UnitPricesModalComponent,
          {
            size: 'lg',
            centered: true,
            backdropClass: 'custom-modal-backdrop'
          });
        newPriceModal.componentInstance.price.subscribe(

          () => {
            const newExtraFieldModal = this.modalService.open(UnitExtraFieldsModalComponent,
              {
                size: 'lg',
                centered: true,
                backdropClass: 'custom-modal-backdrop'
              });
            newExtraFieldModal.componentInstance.service.subscribe();
          }

        );
        //ovde otvaramo novi modul...
      }
    );
  }



}
