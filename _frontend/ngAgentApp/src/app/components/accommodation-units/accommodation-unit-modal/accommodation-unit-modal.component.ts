import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UnitPricesModalComponent } from '../unit-prices-modal/unit-prices-modal.component';
import { AccommodationUnit } from 'src/app/model/accommodation-unit.model';
import { AccommodationUnitService } from 'src/app/services/accommodation-unit.service';
import { Observable } from 'rxjs';
import { Category } from 'src/app/model/category.model';
import { CategoryService } from 'src/app/services/category.service';
import { TypeService } from 'src/app/services/type.service';
import { Type } from 'src/app/model/type.model';

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
      id: [0],
      type: this.formBuilder.group({
        typeId: [0, Validators.min(1)],
        typeName: ['']
      }),
      category: this.formBuilder.group({
        categoryId: [0, Validators.min(1)],
        categoryName: ['']
      }),
      numberOfPeople: ['', Validators.min(1)],

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
    // this.unitForm.controls.numberOfPeople.setValue(this.inputUnit.numberOfPeople);
    // this.unitForm.controls.type.setValue((this.inputUnit.type as Type));
    // this.unitForm.controls.category.setValue((this.inputUnit.category as Category));
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
          this.unit.emit();

        });
      } else {
        const unitToCreate: AccommodationUnit = this.unitForm.value;
        unitToCreate.accommodation = {
          accommodationId: parseInt(localStorage.getItem('accommodation')),
          accommodationName: '',
          description: '',
          agentId: parseInt(localStorage.getItem('agent')),
          numberOfDaysBeforeCancelation: 0,
          city: null,
          picture: null
        };
        this.accommodationUnitService.createUnit(unitToCreate).subscribe((accommodationUnit) => {
          this.activeModal.close();
          this.unit.emit(accommodationUnit);

        });
      }
    }

  }
}
