import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Admin } from 'src/app/model/admin.model';

@Component({
  selector: 'app-admins-single-modal',
  templateUrl: './admins-single-modal.component.html',
  styleUrls: ['./admins-single-modal.component.css']
})
export class AdminsSingleModalComponent implements OnInit {

  @Output() admin: EventEmitter<any> = new EventEmitter();
  adminForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder) {}

  ngOnInit() {

    this.adminForm = this.formBuilder.group({
      id: [''],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.email]
    });
  }

  onSubmit() {
    if (this.adminForm.valid) {
      this.admin.emit(this.adminForm.value as Admin);
      this.activeModal.close();
    }
  }
}

