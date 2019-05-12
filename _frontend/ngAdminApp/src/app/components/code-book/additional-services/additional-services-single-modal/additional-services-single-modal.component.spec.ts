import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdditionalServicesSingleModalComponent } from './additional-services-single-modal.component';

describe('AdditionalServicesSingleModalComponent', () => {
  let component: AdditionalServicesSingleModalComponent;
  let fixture: ComponentFixture<AdditionalServicesSingleModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdditionalServicesSingleModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdditionalServicesSingleModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
