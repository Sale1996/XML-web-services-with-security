import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-agents-single-modal',
  templateUrl: './agents-single-modal.component.html',
  styleUrls: ['./agents-single-modal.component.css']
})
export class AgentsSingleModalComponent implements OnInit {

  @Input() name;
  agentForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder) {}

  ngOnInit() {

    this.agentForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      email: ['', Validators.email],
      telephoneNumber: ['', Validators.pattern('^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$')],
      businessRegistrationNumber: ['', Validators.required]
    });
  }

}
