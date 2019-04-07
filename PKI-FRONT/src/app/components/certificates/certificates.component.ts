import { Certificate } from './../../model/certificate.model';
import { CertificateService } from './../../services/certificate.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { share } from 'rxjs/operators';

@Component({
  selector: 'app-certificates',
  templateUrl: './certificates.component.html',
  styleUrls: ['./certificates.component.css']
})
export class CertificatesComponent implements OnInit {

  certs: Observable<Certificate[]>;

  constructor(
    private certService: CertificateService
  ) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.certs = this.certService.getAllCertificates().pipe(share());
  }

  revoke(serialNumber: string) {
    this.certService.revokeCertificate(serialNumber).subscribe(
      data => console.log(data),
      error => console.log(error),
      () => location.reload()
    );
  }

}
