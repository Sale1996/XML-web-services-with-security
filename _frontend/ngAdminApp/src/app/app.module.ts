import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AgentsSingleModalComponent } from './components/agents/agents-single-modal/agents-single-modal.component';
import { AgentsComponent } from './components/agents/agents.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { UserReviewComponent } from './components/user-review/user-review.component';
import { UsersComponent } from './components/users/users.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    UserReviewComponent,
    UsersComponent,
    AgentsComponent,
    AgentsSingleModalComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [],
  entryComponents: [AgentsSingleModalComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
