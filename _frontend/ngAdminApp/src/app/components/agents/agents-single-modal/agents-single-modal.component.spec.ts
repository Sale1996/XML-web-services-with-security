import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentsSingleModalComponent } from './agents-single-modal.component';

describe('AgentsSingleModalComponent', () => {
  let component: AgentsSingleModalComponent;
  let fixture: ComponentFixture<AgentsSingleModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgentsSingleModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgentsSingleModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
