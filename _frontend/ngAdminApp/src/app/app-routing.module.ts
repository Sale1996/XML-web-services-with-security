import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserReviewComponent } from './components/user-review/user-review.component';
import { UsersComponent } from './components/users/users.component';
import { AgentsComponent } from './components/agents/agents.component';
import { CodeBookComponent } from './components/code-book/code-book.component';
import { AdminsComponent } from './components/admins/admins.component';
import { AuthGuard } from './_shared/auth-guard.service';

const routes: Routes = [
  { path: 'code-book', component: CodeBookComponent, canActivate: [AuthGuard] },
  { path: 'reviews', component: UserReviewComponent, canActivate: [AuthGuard] },
  { path: 'users', component: UsersComponent, canActivate: [AuthGuard] },
  { path: 'agents', component: AgentsComponent, canActivate: [AuthGuard] },
  { path: 'admins', component: AdminsComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'code-book' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
