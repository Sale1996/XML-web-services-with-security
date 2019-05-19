import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { ConfirmationModalComponent } from 'src/app/_shared/confirmation-modal/confirmation-modal.component';
import { AccommodationService } from 'src/app/model/accommodation-service.model';
import { AdditionalService } from 'src/app/services/additional.service';

import {
  AdditionalServicesSingleModalComponent,
} from './additional-services-single-modal/additional-services-single-modal.component';

@Component({
  selector: 'app-additional-services',
  templateUrl: './additional-services.component.html',
  styleUrls: ['./additional-services.component.css']
})
export class AdditionalServicesComponent implements OnInit {

  services$: Observable<AccommodationService[]>;

  constructor(private additionalService: AdditionalService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getAdditionalServices();
  }

  getAdditionalServices() {
    this.services$ = this.additionalService.getAdditionalServices();
  }

  deleteService(service: AccommodationService) {
    const deleteModalRef = this.modalService.open(ConfirmationModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    deleteModalRef.componentInstance.title = 'Delete Service';
    deleteModalRef.componentInstance.message = 'Are you sure you want to delete ' + service.extraFieldName + '?';
    deleteModalRef.componentInstance.answer.subscribe(
      (answer: boolean) => {
        if (answer) {
          this.additionalService.deleteAdditionalService(service.extraFieldId).subscribe(
            () => {
              this.getAdditionalServices();
            }
          );
        }
      }
    );
  }

  openServiceModal(id?: number) {
    const agentModalRef = this.modalService.open(AdditionalServicesSingleModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });

    if (id) {
      agentModalRef.componentInstance.id = id;
    }
    agentModalRef.componentInstance.service.subscribe(
      (service: AccommodationService) => {
          if (service.extraFieldId) {
            this.additionalService.updateAdditionalService(service).subscribe(
              () => {
                this.getAdditionalServices();
              }
            );
          } else {
              this.additionalService.createAdditionalService(service).subscribe(
                () => {
                  this.getAdditionalServices();
                }
              );
          }
      }
    );
  }
}
