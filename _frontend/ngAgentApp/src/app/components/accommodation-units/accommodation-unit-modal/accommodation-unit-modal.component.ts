import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UnitPricesModalComponent } from '../unit-prices-modal/unit-prices-modal.component';
import { AccommodationUnit } from 'src/app/model/accommodation-unit.model';
import { AccommodationUnitService } from 'src/app/services/accommodation-unit.service';
import { Observable } from 'rxjs';
import { Category } from 'src/app/model/category.model';
import { Type } from 'src/app/model/type.model';
import { CategoryService } from 'src/app/services/category.service';
import { TypeService } from 'src/app/services/type.service';

@Component({
  selector: 'app-accommodation-unit-modal',
  templateUrl: './accommodation-unit-modal.component.html',
  styleUrls: ['./accommodation-unit-modal.component.css']
})
export class AccommodationUnitModalComponent implements OnInit {

  @Input() inputUnit?: AccommodationUnit;
  @Input() unitId;
  @Output() unit: EventEmitter<any> = new EventEmitter();

  submitButtonText: string;
  title: string;
  unitForm: FormGroup;
  types$: Observable<Type[]>;
  categories$: Observable<Category[]>;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder, private modalService: NgbModal, private accommodationUnitService: AccommodationUnitService, private typeService: TypeService, private categoryService: CategoryService) { }

  ngOnInit() {

    this.unitForm = this.formBuilder.group({
      id: [''],
      type: [''],
      category: [''],
      numberOfPeople: [''],

    });

    this.getTypesAndCategories();

    if (this.inputUnit) {
      this.submitButtonText = 'Save Changes';
      this.title = "Edit";
      this.patchForm();
    } else {
      this.title = "Create";
      this.submitButtonText = 'Add Accommodation Unit';
    }


  }

  patchForm() {
    this.unitForm.patchValue(this.inputUnit);
  }

  getTypesAndCategories() {
    this.types$ = this.typeService.getTypes();
    this.categories$ = this.categoryService.getCategories();
  }

  onSubmit() {
    if (this.unitForm.valid) {
      if (this.inputUnit) {

        const accommodationUnitToBeChange: AccommodationUnit = this.inputUnit;
        accommodationUnitToBeChange.numberOfPeople = this.unitForm.controls.numberOfPeople.value;
        accommodationUnitToBeChange.type = this.unitForm.controls.type.value;
        accommodationUnitToBeChange.category = this.unitForm.controls.category.value;

        this.accommodationUnitService.updateUnit(accommodationUnitToBeChange as AccommodationUnit).subscribe(() => {
          this.activeModal.close();

        });
      } else {

        const unitToCreate: AccommodationUnit = this.unitForm.value;
        unitToCreate.accommodation = {
          accommodationId: parseInt(localStorage.getItem('accommodation')),
          accommodationName: '',
          description: '',
          agentId: 0,
          numberOfDaysBeforeCancelation: 0,
          city: null,
          picture: null
        };
        this.accommodationUnitService.createUnit(unitToCreate).subscribe(() => {
          this.activeModal.close();

        });
      }
    }

  }
}
