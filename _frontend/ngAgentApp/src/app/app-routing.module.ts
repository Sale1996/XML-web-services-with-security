import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccommodationComponent } from './components/accommodation/accommodation.component';
import { UserReviewComponent } from './components/user-review/user-review.component';
import { InboxComponent } from './components/inbox/inbox.component';
import { AgentsSingleModalComponent } from './components/agents-single-modal/agents-single-modal.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { AccommodationUnitsComponent } from './components/accommodation-units/accommodation-units.component';
import { AuthGuard } from './_shared/auth-guard.service';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [

  { path: 'accommodation', component: AccommodationComponent, canActivate: [AuthGuard] },
  { path: 'user-reviews', component: UserReviewComponent, canActivate: [AuthGuard] },
  { path: 'inbox', component: InboxComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: AgentsSingleModalComponent, canActivate: [AuthGuard] },
  { path: 'reservations', component: ReservationsComponent, canActivate: [AuthGuard] },
  { path: 'accommodation-units', component: AccommodationUnitsComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'accommodation' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
