import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { TypeService } from 'src/app/services/type.service';
import { ConfirmationModalComponent } from 'src/app/_shared/confirmation-modal/confirmation-modal.component';
import { AccommodationTypesSingleModalComponent } from './accommodation-types-single-modal/accommodation-types-single-modal.component';
import { AccommodationType } from 'src/app/model/accommodation-type.model';

@Component({
  selector: 'app-accommodation-types',
  templateUrl: './accommodation-types.component.html',
  styleUrls: ['./accommodation-types.component.css']
})
export class AccommodationTypesComponent implements OnInit {

  types$: Observable<AccommodationType[]>;

  constructor(private typeService: TypeService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getTypes();
  }

  getTypes() {
    this.types$ = this.typeService.getTypes();
  }

  deleteType(type: AccommodationType) {
    const deleteModalRef = this.modalService.open(ConfirmationModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    deleteModalRef.componentInstance.title = 'Delete Type';
    deleteModalRef.componentInstance.message = 'Are you sure you want to delete ' + type.typeName + '?';
    deleteModalRef.componentInstance.answer.subscribe(
      (answer: boolean) => {
        if (answer) {
          this.typeService.deleteType(type.typeId).subscribe(
            () => {
              this.getTypes();
            }
          );
        }
      }
    );
  }

  openTypeModal(id?: number) {
    const agentModalRef = this.modalService.open(AccommodationTypesSingleModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });

    if (id) {
      agentModalRef.componentInstance.id = id;
    }
    agentModalRef.componentInstance.type.subscribe(
      (type: AccommodationType) => {
          if (type.typeId) {
            this.typeService.updateType(type).subscribe(
              () => {
                this.getTypes();
              }
            );
          } else {
              this.typeService.createType(type).subscribe(
                () => {
                  this.getTypes();
                }
              );
          }
      }
    );
  }
}

