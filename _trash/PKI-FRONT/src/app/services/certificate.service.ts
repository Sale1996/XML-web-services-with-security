import { Certificate } from './../model/certificate.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

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

  addCertificate(cert: Certificate): Observable<Object> {
    return this.http.post<Certificate>(`${environment.apiUrl}` + '/certificate/', cert, httpOptions);
  }

  revokeCertificate(serialNumber: string): Observable<Certificate> {
    return this.http.put<Certificate>(`${environment.apiUrl}` + '/certificate/revoke/' + serialNumber, {reason: 'Expired'}, httpOptions);
  }
}
