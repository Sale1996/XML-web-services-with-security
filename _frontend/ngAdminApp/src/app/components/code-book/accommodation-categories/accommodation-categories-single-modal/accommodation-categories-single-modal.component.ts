import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-accommodation-categories-single-modal',
  templateUrl: './accommodation-categories-single-modal.component.html',
  styleUrls: ['./accommodation-categories-single-modal.component.css']
})
export class AccommodationCategoriesSingleModalComponent implements OnInit {

  @Input() id?: number;
  submitBtnText: string;
  accommodationCategoryForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder) {}

  ngOnInit() {

    this.accommodationCategoryForm = this.formBuilder.group({
      categoryId: [''],
      categoryName: ['', Validators.required]
    });

    if (this.id) {
      this.submitBtnText = 'Save Changes';
      this.getAccommodationCategoryById(this.id);
    } else {
      this.submitBtnText = 'Add Category';
    }
  }

  getAccommodationCategoryById(id: number) {
    this.accommodationCategoryForm.patchValue({categoryId: 1, categoryName: 'Demo'});
  }

}
