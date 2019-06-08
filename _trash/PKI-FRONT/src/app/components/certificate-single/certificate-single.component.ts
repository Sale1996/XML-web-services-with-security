import { CertificateService } from './../../services/certificate.service';
import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { Certificate } from './../../model/certificate.model';
import { Observable } from 'rxjs';
import { share } from 'rxjs/operators';

@Component({
  selector: 'app-certificate-single',
  templateUrl: './certificate-single.component.html',
  styleUrls: ['./certificate-single.component.css']
})
export class CertificateSingleComponent implements OnInit {

  CertificateForm: FormGroup;
  certs: Observable<Certificate[]>

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private certService: CertificateService
  ) { }

  ngOnInit() {

    this.CertificateForm = this.formBuilder.group({
      commonName: ['', Validators.required],
      country: ['', Validators.required],
      locality: ['', Validators.required],
      state: ['', Validators.required],
      organisation: ['', Validators.required],
      organisationUnit: ['', Validators.required],
      validFromDate: ['', Validators.required],
      validToDate: ['', Validators.required],
      type: [0, Validators.required],
      issuer: [0, Validators.required],
      active: true
    });
  }

  onSubmit() {
    if(this.CertificateForm.valid) {
        this.certService.addCertificate(this.CertificateForm.value).subscribe((response) => {
          console.log('Response is: ', response);
        });
    }
  }



}
