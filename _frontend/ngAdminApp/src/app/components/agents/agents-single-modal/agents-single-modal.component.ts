import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Agent } from 'src/app/model/agent.model';

@Component({
  selector: 'app-agents-single-modal',
  templateUrl: './agents-single-modal.component.html',
  styleUrls: ['./agents-single-modal.component.css']
})
export class AgentsSingleModalComponent implements OnInit {

  @Output() agent: EventEmitter<any> = new EventEmitter();
  agentForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder) {}

  ngOnInit() {

    this.agentForm = this.formBuilder.group({
      id: [''],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.email],
      telephoneNumber: ['', Validators.pattern('^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$')],
      businessRegistrationNumber: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.agentForm.valid) {
      this.agent.emit(this.agentForm.value as Agent);
      this.activeModal.close();
    }
  }

}
