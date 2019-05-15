import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from './../../model/user.model';
import { Observable, Subject, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { ConfirmationModalComponent } from 'src/app/_shared/confirmation-modal/confirmation-modal.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users$: Observable<User[]>;

  constructor(private userService: UserService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.users$ = this.userService.getUsers();
  }

  changeStatusUser(user: User, status: boolean) {
    const deleteModalRef = this.modalService.open(ConfirmationModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    deleteModalRef.componentInstance.title = status ? 'Unblock User' : 'Block User';
    deleteModalRef.componentInstance.message = 'Are you sure you want to ' + (status ? 'unblock ' : 'block ') + user.firstName + ' ' + user.lastName + '?';
    deleteModalRef.componentInstance.answer.subscribe(
      (answer: boolean) => {
        if (answer) {
          user.activated = status;
          this.userService.changeStatusUser(user).subscribe(
            () => {
              this.getUsers();
            }
          );
        }
      }
    );
  }

  deleteUser(user: User) {
    const deleteModalRef = this.modalService.open(ConfirmationModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    deleteModalRef.componentInstance.title = 'Delete User';
    deleteModalRef.componentInstance.message = 'Are you sure you want to delete ' + user.firstName + ' ' + user.lastName + '?';
    deleteModalRef.componentInstance.answer.subscribe(
      (answer: boolean) => {
        if (answer) {
          this.userService.deleteUser(user.id).subscribe(
            () => {
              this.getUsers();
            }
          );
        }
      }
    );
  }
}
