import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { ConfirmationModalComponent } from 'src/app/_shared/confirmation-modal/confirmation-modal.component';

import { Admin } from './../../model/admin.model';
import { AdminService } from './../../services/admin.service';
import { AdminsSingleModalComponent } from './admins-single-modal/admins-single-modal.component';

@Component({
  selector: 'app-admins',
  templateUrl: './admins.component.html',
  styleUrls: ['./admins.component.css']
})
export class AdminsComponent implements OnInit {

  admins$: Observable<Admin[]>;

  constructor(private adminService: AdminService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getAdmins();
  }

  getAdmins() {
    this.admins$ = this.adminService.getAdmins();
  }

  deleteAdmin(admin: Admin) {
    const deleteModalRef = this.modalService.open(ConfirmationModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    deleteModalRef.componentInstance.title = 'Delete Admin';
    deleteModalRef.componentInstance.message = 'Are you sure you want to delete ' + admin.firstName + ' ' + admin.lastName + '?';
    deleteModalRef.componentInstance.answer.subscribe(
      (answer: boolean) => {
        if (answer) {
          this.adminService.deleteAdmin(admin.id).subscribe(
            () => {
              this.getAdmins();
            }
          );
        }
      }
    );
  }

  openAdminModal() {
    const adminModalRef = this.modalService.open(AdminsSingleModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    adminModalRef.componentInstance.admin.subscribe(
      (admin: Admin) => {
          if (admin) {
            this.adminService.createAdmin(admin).subscribe(
              () => {
                this.getAdmins();
              }
            );
          }
      }
    );
  }
}
