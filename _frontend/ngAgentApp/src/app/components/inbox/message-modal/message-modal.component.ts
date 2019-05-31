import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-message-modal',
  templateUrl: './message-modal.component.html',
  styleUrls: ['./message-modal.component.css']
})
export class MessageModalComponent implements OnInit {

  @Input() id?: number;
  @Output() messageEmiter: EventEmitter<any> = new EventEmitter();
  messageForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.messageForm = this.formBuilder.group({
      id: [''],
      message: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.messageForm.valid) {
      this.messageEmiter.emit(this.messageForm.value); //ovde treba this.messageForm as Message...gde je message klasa
      this.activeModal.close();
    }
  }

}
