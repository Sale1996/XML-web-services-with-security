import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CertificateSingleComponent } from './certificate-single.component';

describe('CertificateSingleComponent', () => {
  let component: CertificateSingleComponent;
  let fixture: ComponentFixture<CertificateSingleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CertificateSingleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CertificateSingleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
