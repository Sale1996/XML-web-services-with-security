import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnitPricesModalComponent } from './unit-prices-modal.component';

describe('UnitPricesModalComponent', () => {
  let component: UnitPricesModalComponent;
  let fixture: ComponentFixture<UnitPricesModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnitPricesModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnitPricesModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
