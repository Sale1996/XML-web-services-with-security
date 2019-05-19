import { AccommodationCategoriesSingleModalComponent } from './accommodation-categories-single-modal/accommodation-categories-single-modal.component';
import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { ConfirmationModalComponent } from 'src/app/_shared/confirmation-modal/confirmation-modal.component';
import { AccommodationCategory } from 'src/app/model/accommodation-category.model';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-accommodation-categories',
  templateUrl: './accommodation-categories.component.html',
  styleUrls: ['./accommodation-categories.component.css']
})
export class AccommodationCategoriesComponent implements OnInit {

  categories$: Observable<AccommodationCategory[]>;

  constructor(private categoryService: CategoryService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getCategories();
  }

  getCategories() {
    this.categories$ = this.categoryService.getCategories();
  }

  deleteCategory(category: AccommodationCategory) {
    const deleteModalRef = this.modalService.open(ConfirmationModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    deleteModalRef.componentInstance.title = 'Delete Category';
    deleteModalRef.componentInstance.message = 'Are you sure you want to delete ' + category.categoryName + '?';
    deleteModalRef.componentInstance.answer.subscribe(
      (answer: boolean) => {
        if (answer) {
          this.categoryService.deleteCategory(category.categoryId).subscribe(
            () => {
              this.getCategories();
            }
          );
        }
      }
    );
  }

  openCategoryModal(id?: number) {
    const agentModalRef = this.modalService.open(AccommodationCategoriesSingleModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });

    if (id) {
      agentModalRef.componentInstance.id = id;
    }
    agentModalRef.componentInstance.category.subscribe(
      (category: AccommodationCategory) => {
          if (category.categoryId) {
            this.categoryService.updateCategory(category).subscribe(
              () => {
                this.getCategories();
              }
            );
          } else {
              this.categoryService.createCategory(category).subscribe(
                () => {
                  this.getCategories();
                }
              );
          }
      }
    );
  }
}