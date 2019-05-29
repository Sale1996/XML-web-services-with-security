import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccommodationUnitModalComponent } from './accommodation-unit-modal.component';

describe('AccommodationUnitModalComponent', () => {
  let component: AccommodationUnitModalComponent;
  let fixture: ComponentFixture<AccommodationUnitModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccommodationUnitModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccommodationUnitModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
