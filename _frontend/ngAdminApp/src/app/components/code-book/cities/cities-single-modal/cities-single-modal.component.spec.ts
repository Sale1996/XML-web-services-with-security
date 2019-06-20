import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CitiesSingleModalComponent } from './cities-single-modal.component';

describe('CitiesSingleModalComponent', () => {
  let component: CitiesSingleModalComponent;
  let fixture: ComponentFixture<CitiesSingleModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CitiesSingleModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CitiesSingleModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
