import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccommodationComponent } from './components/accommodation/accommodation.component';
import { UserReviewComponent } from './components/user-review/user-review.component';
import { InboxComponent } from './components/inbox/inbox.component';
import { AgentsSingleModalComponent } from './components/agents-single-modal/agents-single-modal.component';

const routes: Routes = [

  { path: 'accommodation', component: AccommodationComponent },
  { path: 'user-reviews', component: UserReviewComponent },
  { path: 'inbox', component: InboxComponent },
  { path: 'profile', component: AgentsSingleModalComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
