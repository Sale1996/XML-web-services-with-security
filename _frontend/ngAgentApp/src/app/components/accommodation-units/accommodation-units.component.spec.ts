import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccommodationUnitsComponent } from './accommodation-units.component';

describe('AccommodationUnitsComponent', () => {
  let component: AccommodationUnitsComponent;
  let fixture: ComponentFixture<AccommodationUnitsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccommodationUnitsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccommodationUnitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
