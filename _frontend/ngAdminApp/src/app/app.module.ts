import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ErrorHandler, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { GlobalErrorHandler } from './_helpers/global-error-handler';
import { HttpErrorInterceptor } from './_helpers/http-error-interceptor';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminsSingleModalComponent } from './components/admins/admins-single-modal/admins-single-modal.component';
import { AdminsComponent } from './components/admins/admins.component';
import { AgentsSingleModalComponent } from './components/agents/agents-single-modal/agents-single-modal.component';
import { AgentsComponent } from './components/agents/agents.component';
import {
  AccommodationCategoriesSingleModalComponent,
} from './components/code-book/accommodation-categories/accommodation-categories-single-modal/accommodation-categories-single-modal.component';
import {
  AccommodationCategoriesComponent,
} from './components/code-book/accommodation-categories/accommodation-categories.component';
import {
  AccommodationTypesSingleModalComponent,
} from './components/code-book/accommodation-types/accommodation-types-single-modal/accommodation-types-single-modal.component';
import { AccommodationTypesComponent } from './components/code-book/accommodation-types/accommodation-types.component';
import {
  AdditionalServicesSingleModalComponent,
} from './components/code-book/additional-services/additional-services-single-modal/additional-services-single-modal.component';
import { AdditionalServicesComponent } from './components/code-book/additional-services/additional-services.component';
import { CodeBookComponent } from './components/code-book/code-book.component';
import { LoginComponent } from './components/login/login.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { UserReviewComponent } from './components/user-review/user-review.component';
import { UsersComponent } from './components/users/users.component';
import { ConfirmationModalComponent } from './_shared/confirmation-modal/confirmation-modal.component';
import { JwtInterceptor } from './_helpers/jwt.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    UserReviewComponent,
    UsersComponent,
    AgentsComponent,
    AgentsSingleModalComponent,
    LoginComponent,
    CodeBookComponent,
    AccommodationTypesComponent,
    AccommodationTypesSingleModalComponent,
    AccommodationCategoriesComponent,
    AccommodationCategoriesSingleModalComponent,
    AdditionalServicesComponent,
    AdditionalServicesSingleModalComponent,
    AdminsComponent,
    AdminsSingleModalComponent,
    ConfirmationModalComponent
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
      provide: ErrorHandler,
      useClass: GlobalErrorHandler
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  entryComponents: [
    AgentsSingleModalComponent,
    AdminsSingleModalComponent,
    AccommodationTypesSingleModalComponent,
    AccommodationCategoriesSingleModalComponent,
    AdditionalServicesSingleModalComponent,
    ConfirmationModalComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
