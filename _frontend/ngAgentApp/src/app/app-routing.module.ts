import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccommodationComponent } from './components/accommodation/accommodation.component';
import { UserReviewComponent } from './components/user-review/user-review.component';
import { InboxComponent } from './components/inbox/inbox.component';
import { AgentsSingleModalComponent } from './components/agents-single-modal/agents-single-modal.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { AccommodationUnitsComponent } from './components/accommodation-units/accommodation-units.component';

const routes: Routes = [

  { path: 'accommodation', component: AccommodationComponent },
  { path: 'user-reviews', component: UserReviewComponent },
  { path: 'inbox', component: InboxComponent },
  { path: 'profile', component: AgentsSingleModalComponent },
  { path: 'reservations', component: ReservationsComponent },
  { path: 'accommodation-units', component: AccommodationUnitsComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
