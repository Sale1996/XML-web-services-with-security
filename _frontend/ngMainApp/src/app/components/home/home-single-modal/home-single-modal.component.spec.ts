import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeSingleModalComponent } from './home-single-modal.component';

describe('HomeSingleModalComponent', () => {
  let component: HomeSingleModalComponent;
  let fixture: ComponentFixture<HomeSingleModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeSingleModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeSingleModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
