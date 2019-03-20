import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { Certificate } from './../../model/certificate.model';
import { Request } from 'src/app/model/request.model';

@Component({
  selector: 'app-certificate-single',
  templateUrl: './certificate-single.component.html',
  styleUrls: ['./certificate-single.component.css']
})
export class CertificateSingleComponent implements OnInit {

  CertificateForm: FormGroup;
  request: Request;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {

    this.CertificateForm = this.formBuilder.group({
      name: ['', Validators.required],
      date: [new Date(), Validators.required],
      issuer: [0, Validators.required],
      publicKey: ['', Validators.required],
      keySize: [0, Validators.required],
      country: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      organization: ['', Validators.required],
      organizationUnit: ['', Validators.required],
      type: [0, Validators.required]
    });
  }

  onSubmit() {
  }
}
