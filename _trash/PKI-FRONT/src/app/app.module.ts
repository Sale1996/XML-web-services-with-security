import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CertificateSingleComponent } from './components/certificate-single/certificate-single.component';
import { CertificatesComponent } from './components/certificates/certificates.component';
import { LoginComponent } from './components/login/login.component';
import { RequestsComponent } from './components/requests/requests.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CertificatesComponent,
    CertificateSingleComponent,
    RequestsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
