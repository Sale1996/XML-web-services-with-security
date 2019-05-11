import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccommodationTypesSingleModalComponent } from './accommodation-types-single-modal.component';

describe('AccommodationTypesSingleModalComponent', () => {
  let component: AccommodationTypesSingleModalComponent;
  let fixture: ComponentFixture<AccommodationTypesSingleModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccommodationTypesSingleModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccommodationTypesSingleModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
