import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';

import { ConfirmationModalComponent } from './../../_shared/confirmation-modal/confirmation-modal.component';
import { Agent } from './../../model/agent.model';
import { AgentService } from './../../services/agent.service';
import { AgentsSingleModalComponent } from './agents-single-modal/agents-single-modal.component';

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.css']
})
export class AgentsComponent implements OnInit {

  agents$: Observable<Agent[]>;

  constructor(private agentService: AgentService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getAgents();
  }

  getAgents() {
    this.agents$ = this.agentService.getAgents();
  }

  changeStatusAgent(agent: Agent, status: boolean) {
    const deleteModalRef = this.modalService.open(ConfirmationModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    deleteModalRef.componentInstance.title = status ? 'Unblock User' : 'Block Agent';
    deleteModalRef.componentInstance.message = 'Are you sure you want to ' + (status ? 'unblock ' : 'block ') + agent.firstName + ' ' + agent.lastName + '?';
    deleteModalRef.componentInstance.answer.subscribe(
      (answer: boolean) => {
        if (answer) {
          agent.activated = status;
          this.agentService.changeStatusAgent(agent).subscribe(
            () => {
              this.getAgents();
            }
          );
        }
      }
    );
  }

  deleteAgent(agent: Agent) {
    const deleteModalRef = this.modalService.open(ConfirmationModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    deleteModalRef.componentInstance.title = 'Delete Agent';
    deleteModalRef.componentInstance.message = 'Are you sure you want to delete ' + agent.firstName + ' ' + agent.lastName + '?';
    deleteModalRef.componentInstance.answer.subscribe(
      (answer: boolean) => {
        if (answer) {
          this.agentService.deleteAgent(agent.id).subscribe(
            () => {
              this.getAgents();
            }
          );
        }
      }
    );
  }

  openAgentModal() {
    const agentModalRef = this.modalService.open(AgentsSingleModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    agentModalRef.componentInstance.agent.subscribe(
      (agent: Agent) => {
          if (agent) {
            this.agentService.createAgent(agent).subscribe(
              () => {
                this.getAgents();
              }
            );
          }
      }
    );
  }
}

