import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AdditionalServicesSingleModalComponent } from './additional-services-single-modal/additional-services-single-modal.component';

@Component({
  selector: 'app-additional-services',
  templateUrl: './additional-services.component.html',
  styleUrls: ['./additional-services.component.css']
})
export class AdditionalServicesComponent implements OnInit {

  constructor(private modalService: NgbModal) {}

  ngOnInit() {
  }

  open(id?: number) {
    const modalRef = this.modalService.open(AdditionalServicesSingleModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    modalRef.componentInstance.id = id ? id : null;
  }

}