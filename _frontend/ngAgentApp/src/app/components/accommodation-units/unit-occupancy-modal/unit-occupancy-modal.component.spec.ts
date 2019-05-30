import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnitOccupancyModalComponent } from './unit-occupancy-modal.component';

describe('UnitOccupancyModalComponent', () => {
  let component: UnitOccupancyModalComponent;
  let fixture: ComponentFixture<UnitOccupancyModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnitOccupancyModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnitOccupancyModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
