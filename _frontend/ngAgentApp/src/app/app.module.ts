import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { AccommodationComponent } from './components/accommodation/accommodation.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { UserReviewComponent } from './components/user-review/user-review.component';
import { InboxComponent } from './components/inbox/inbox.component';
import { AgentsSingleModalComponent } from './components/agents-single-modal/agents-single-modal.component';
import { MessageModalComponent } from './components/inbox/message-modal/message-modal.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { AccommodationUnitsComponent } from './components/accommodation-units/accommodation-units.component';
import { AccommodationUnitModalComponent } from './components/accommodation-units/accommodation-unit-modal/accommodation-unit-modal.component';
import { UnitPricesModalComponent } from './components/accommodation-units/unit-prices-modal/unit-prices-modal.component';
import { UnitExtraFieldsModalComponent } from './components/accommodation-units/unit-extra-fields-modal/unit-extra-fields-modal.component';
import { UnitOccupancyModalComponent } from './components/accommodation-units/unit-occupancy-modal/unit-occupancy-modal.component';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import { LoginComponent } from './components/login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AccommodationComponent,
    UserReviewComponent,
    InboxComponent,
    AgentsSingleModalComponent,
    MessageModalComponent,
    ReservationsComponent,
    AccommodationUnitsComponent,
    AccommodationUnitModalComponent,
    UnitPricesModalComponent,
    UnitExtraFieldsModalComponent,
    UnitOccupancyModalComponent,
    LoginComponent

  ],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    MessageModalComponent,
    AccommodationUnitModalComponent,
    UnitPricesModalComponent,
    UnitExtraFieldsModalComponent,
    UnitOccupancyModalComponent
  ]
})
export class AppModule { }
