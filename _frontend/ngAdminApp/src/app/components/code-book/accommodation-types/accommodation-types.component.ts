import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AccommodationTypesSingleModalComponent } from './accommodation-types-single-modal/accommodation-types-single-modal.component';

@Component({
  selector: 'app-accommodation-types',
  templateUrl: './accommodation-types.component.html',
  styleUrls: ['./accommodation-types.component.css']
})
export class AccommodationTypesComponent implements OnInit {

  constructor(private modalService: NgbModal) {}

  ngOnInit() {
  }

  open() {
    const modalRef = this.modalService.open(AccommodationTypesSingleModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    modalRef.componentInstance.name = 'World';
  }

}
