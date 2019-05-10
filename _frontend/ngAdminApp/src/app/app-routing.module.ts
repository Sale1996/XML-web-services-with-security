import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserReviewComponent } from './components/user-review/user-review.component';
import { UsersComponent } from './components/users/users.component';
import { AgentsComponent } from './components/agents/agents.component';

const routes: Routes = [
  { path: 'reviews', component: UserReviewComponent },
  { path: 'users', component: UsersComponent },
  { path: 'agents', component: AgentsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
