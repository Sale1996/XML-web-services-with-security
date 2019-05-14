import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminsSingleModalComponent } from './admins-single-modal.component';

describe('AdminsSingleModalComponent', () => {
  let component: AdminsSingleModalComponent;
  let fixture: ComponentFixture<AdminsSingleModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminsSingleModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminsSingleModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
