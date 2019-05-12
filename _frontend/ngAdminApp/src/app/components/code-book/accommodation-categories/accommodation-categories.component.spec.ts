import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccommodationCategoriesComponent } from './accommodation-categories.component';

describe('AccommodationCategoriesComponent', () => {
  let component: AccommodationCategoriesComponent;
  let fixture: ComponentFixture<AccommodationCategoriesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccommodationCategoriesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccommodationCategoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
