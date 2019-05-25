import { InboxComponent } from './components/inbox/inbox.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileEditComponent } from './components/profile/profile-edit/profile-edit.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AccommodationListComponent } from './components/accommodations/accommodation-list/accommodation-list.component';
import { HomeComponent } from './components/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistrationComponent } from './components/registration/registration.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'accommodations', component: AccommodationListComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'profile/edit', component: ProfileEditComponent },
  { path: 'profile/inbox', component: InboxComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
