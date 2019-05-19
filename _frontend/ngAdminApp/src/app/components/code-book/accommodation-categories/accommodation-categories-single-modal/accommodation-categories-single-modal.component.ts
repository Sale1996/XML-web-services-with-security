import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from 'src/app/services/category.service';
import { AccommodationCategory } from 'src/app/model/accommodation-category.model';

@Component({
  selector: 'app-accommodation-categories-single-modal',
  templateUrl: './accommodation-categories-single-modal.component.html',
  styleUrls: ['./accommodation-categories-single-modal.component.css']
})
export class AccommodationCategoriesSingleModalComponent implements OnInit {

  @Input() id?: number;
  @Output() category: EventEmitter<any> = new EventEmitter();
  submitBtnText: string;
  accommodationCategoryForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder, private categoryService: CategoryService) {}

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
    this.categoryService.getCategoryById(id).subscribe(
      (category: AccommodationCategory) => this.accommodationCategoryForm.patchValue(category)
    );
  }

  onSubmit() {
    if (this.accommodationCategoryForm.valid) {
      this.category.emit(this.accommodationCategoryForm.value as AccommodationCategory);
      this.activeModal.close();
    }
  }

}
