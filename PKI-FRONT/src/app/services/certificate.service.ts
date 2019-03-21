import { Certificate } from './../model/certificate.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CertificateService {

  constructor(
    private http: HttpClient
  ) { }

  getAllCertificates(): Observable<Certificate[]> {
    return this.http.get<Certificate[]>(`${environment.apiUrl}` + '/certificate/');
  }
}
