import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { CertificatesComponent } from './components/certificates/certificates.component';
import { CertificateSingleComponent } from './components/certificate-single/certificate-single.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'certificate',
    component: CertificatesComponent
  },
  {
    path: 'certificate/new',
    component: CertificateSingleComponent
  },
  { path: '',
    redirectTo: 'certificate',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
