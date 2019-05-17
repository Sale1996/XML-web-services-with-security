import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import {
  AccommodationCategoriesSingleModalComponent,
} from './accommodation-categories-single-modal/accommodation-categories-single-modal.component';

@Component({
  selector: 'app-accommodation-categories',
  templateUrl: './accommodation-categories.component.html',
  styleUrls: ['./accommodation-categories.component.css']
})
export class AccommodationCategoriesComponent implements OnInit {

  constructor(private modalService: NgbModal) {}

  ngOnInit() {
  }

  open(id?: number) {
    const modalRef = this.modalService.open(AccommodationCategoriesSingleModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    modalRef.componentInstance.id = id ? id : null;
  }

}