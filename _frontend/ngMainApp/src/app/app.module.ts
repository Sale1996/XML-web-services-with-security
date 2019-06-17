import { JwtInterceptor } from './_helpers/jwt.interceptor';
import { HttpClient } from 'selenium-webdriver/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { HomeComponent } from './components/home/home.component';
import { AccommodationsComponent } from './components/accommodations/accommodations.component';
import { AccommodationListComponent } from './components/accommodations/accommodation-list/accommodation-list.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ProfileComponent } from './components/profile/profile.component';
import { ProfileEditComponent } from './components/profile/profile-edit/profile-edit.component';
import { LoginComponent } from './components/login/login.component';
import { InboxComponent } from './components/inbox/inbox.component';
import { NgSelectModule } from '@ng-select/ng-select';
import { HomeSingleModalComponent } from './components/home/home-single-modal/home-single-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    HomeComponent,
    AccommodationsComponent,
    AccommodationListComponent,
    RegistrationComponent,
    ProfileComponent,
    ProfileEditComponent,
    LoginComponent,
    InboxComponent,
    HomeSingleModalComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    NgSelectModule,
    HttpClientModule,
    AppRoutingModule
  ],
  entryComponents: [
    HomeSingleModalComponent
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
