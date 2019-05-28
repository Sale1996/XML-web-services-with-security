import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccommodationComponent } from './components/accommodation/accommodation.component';
import { UserReviewComponent } from './components/user-review/user-review.component';

const routes: Routes = [

  { path: 'accommodation', component: AccommodationComponent },
  { path: 'user-reviews', component: UserReviewComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
