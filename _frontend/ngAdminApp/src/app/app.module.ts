import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
import { HttpClientModule } from '@angular/common/http';
import { AdminsComponent } from './components/admins/admins.component';
import { AdminsSingleModalComponent } from './components/admins/admins-single-modal/admins-single-modal.component';

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
    AdminsSingleModalComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  entryComponents: [
    AgentsSingleModalComponent,
    AccommodationTypesSingleModalComponent,
    AccommodationCategoriesSingleModalComponent,
    AdditionalServicesSingleModalComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
