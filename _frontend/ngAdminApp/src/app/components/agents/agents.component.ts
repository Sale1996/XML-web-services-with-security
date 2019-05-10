import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AgentsSingleModalComponent } from './agents-single-modal/agents-single-modal.component';

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.css']
})
export class AgentsComponent implements OnInit {

  constructor(private modalService: NgbModal) {}

  ngOnInit() {
  }

  open() {
    const modalRef = this.modalService.open(AgentsSingleModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    modalRef.componentInstance.name = 'World';
  }

}
