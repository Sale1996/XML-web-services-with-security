import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Reservation } from 'src/app/model/reservation.model';
import { Observable } from 'rxjs';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-unit-occupancy-modal',
  templateUrl: './unit-occupancy-modal.component.html',
  styleUrls: ['./unit-occupancy-modal.component.css']
})
export class UnitOccupancyModalComponent implements OnInit {

  @Input() unitId: number;
  @Output() occupancy: EventEmitter<any> = new EventEmitter();
  occupancyForm: FormGroup;
  occupancies$: Observable<Reservation[]>;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder, private reservationService: ReservationService) { }

  ngOnInit() {

    this.occupancyForm = this.formBuilder.group({
      id: [''],
      dateFrom: ['', Validators.required],
      dateTo: ['', Validators.required],

    });

    this.getAllOccupancies();
  }

  getAllOccupancies() {
    this.occupancies$ = this.reservationService.getOccupancies(this.unitId);
  }

  onSubmit() {
    if (this.occupancyForm.valid) {
      var occupancy: Reservation = {
        reservationId: -1,
        dateFrom: this.occupancyForm.value.dateFrom,
        dateTo: this.occupancyForm.value.dateTo,
        finalPrice: 0,
        client: 0,
        confirmation: true,
        accommodationUnit: this.unitId
      }

      this.reservationService.createOccupancy(occupancy).subscribe(() => {
        this.getAllOccupancies();
      }
      );
    }
  }

  deleteOccupancy(id: number) {
    this.reservationService.deleteOccupancy(id).subscribe(() => {
      this.getAllOccupancies();
    })
  }

}
