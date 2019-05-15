import { AgentsSingleModalComponent } from './../agents/agents-single-modal/agents-single-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit } from '@angular/core';
import { AdminsSingleModalComponent } from './admins-single-modal/admins-single-modal.component';

@Component({
  selector: 'app-admins',
  templateUrl: './admins.component.html',
  styleUrls: ['./admins.component.css']
})
export class AdminsComponent implements OnInit {

  constructor(private modalService: NgbModal) {}

  ngOnInit() {
  }

  open() {
    const modalRef = this.modalService.open(AdminsSingleModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    modalRef.componentInstance.name = 'World';
  }

}