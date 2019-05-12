import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccommodationCategoriesSingleModalComponent } from './accommodation-categories-single-modal.component';

describe('AccommodationCategoriesSingleModalComponent', () => {
  let component: AccommodationCategoriesSingleModalComponent;
  let fixture: ComponentFixture<AccommodationCategoriesSingleModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccommodationCategoriesSingleModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccommodationCategoriesSingleModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
