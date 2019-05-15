import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserReviewComponent } from './components/user-review/user-review.component';
import { UsersComponent } from './components/users/users.component';
import { AgentsComponent } from './components/agents/agents.component';
import { CodeBookComponent } from './components/code-book/code-book.component';
import { AdminsComponent } from './components/admins/admins.component';

const routes: Routes = [
  { path: 'code-book', component: CodeBookComponent },
  { path: 'reviews', component: UserReviewComponent },
  { path: 'users', component: UsersComponent },
  { path: 'agents', component: AgentsComponent },
  { path: 'admins', component: AdminsComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
