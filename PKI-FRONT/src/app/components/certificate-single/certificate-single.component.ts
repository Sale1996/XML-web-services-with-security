import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-certificate-single',
  templateUrl: './certificate-single.component.html',
  styleUrls: ['./certificate-single.component.css']
})
export class CertificateSingleComponent implements OnInit {

  CertificateForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.CertificateForm = this.formBuilder.group({
      name: ['', Validators.required],
      date: [new Date(), Validators.required],
      issuer: ['', Validators.required],
      type: [0, Validators.required]
    });
  }

  onSubmit() {
  }
}
