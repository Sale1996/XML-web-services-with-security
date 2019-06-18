import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AccommodationUnitModalComponent } from './accommodation-unit-modal/accommodation-unit-modal.component';
import { UnitPricesModalComponent } from './unit-prices-modal/unit-prices-modal.component';
import { UnitExtraFieldsModalComponent } from './unit-extra-fields-modal/unit-extra-fields-modal.component';
import { UnitOccupancyModalComponent } from './unit-occupancy-modal/unit-occupancy-modal.component';
import { AccommodationUnit } from 'src/app/model/accommodation-unit.model';
import { Observable } from 'rxjs';
import { AccommodationUnitService } from 'src/app/services/accommodation-unit.service';

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
  accommmodaitonUnits$: Observable<AccommodationUnit[]>;


  constructor(private modalService: NgbModal, private unitService: AccommodationUnitService) {
    this.pageSize = this.pageSizes[0];

  }

  ngOnInit() {
    this.getAllUnits();
  }


  getAllUnits() {
    this.accommmodaitonUnits$ = this.unitService.getUnits();
  }

  deleteUnit(id: number) {
    this.unitService.deleteUnit(id).subscribe((data) => {
      this.getAllUnits();
    });
  }

  openUnitOccupancyModal(id: number) {
    const newUnitOccupancyModalRef = this.modalService.open(UnitOccupancyModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    newUnitOccupancyModalRef.componentInstance.unitId = id;
    newUnitOccupancyModalRef.componentInstance.occupancy.subscribe();

  }


  openNewUnitModal() {
    const newUnitModalRef = this.modalService.open(AccommodationUnitModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    newUnitModalRef.componentInstance.unit.subscribe(
      (unit: AccommodationUnit) => {
        //pravimo akomodaciju
        this.unitService.createUnit(unit).subscribe((data: AccommodationUnit) => {

          const newPriceModal = this.modalService.open(UnitPricesModalComponent,
            {
              size: 'lg',
              centered: true,
              backdropClass: 'custom-modal-backdrop'
            });
          newPriceModal.componentInstance.unitId = data.accommodationUnitId;
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

        });


        //ovde otvaramo novi modul...
      }
    );
  }


  openChangeUnitModal() {
    const newUnitModalRef = this.modalService.open(AccommodationUnitModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    newUnitModalRef.componentInstance.unit.subscribe();
  }

  openPricesModal(unit: AccommodationUnit) {
    const newPriceModal = this.modalService.open(UnitPricesModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    newPriceModal.componentInstance.unit = unit;
    newPriceModal.componentInstance.price.subscribe();
  }

  openExtraFiledsModal(id: number) {
    const newExtraFieldModal = this.modalService.open(UnitExtraFieldsModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    newExtraFieldModal.componentInstance.id = id;
    newExtraFieldModal.componentInstance.service.subscribe();
  }

}
