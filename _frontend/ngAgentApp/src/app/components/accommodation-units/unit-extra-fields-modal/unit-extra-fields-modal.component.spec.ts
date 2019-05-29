import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnitExtraFieldsModalComponent } from './unit-extra-fields-modal.component';

describe('UnitExtraFieldsModalComponent', () => {
  let component: UnitExtraFieldsModalComponent;
  let fixture: ComponentFixture<UnitExtraFieldsModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnitExtraFieldsModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnitExtraFieldsModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
