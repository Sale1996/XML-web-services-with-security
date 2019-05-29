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



@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AccommodationComponent,
    UserReviewComponent,
    InboxComponent,
    AgentsSingleModalComponent,
    MessageModalComponent
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
  bootstrap: [AppComponent],
  entryComponents: [
    MessageModalComponent
  ]
})
export class AppModule { }
