import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Agent } from 'src/app/model/agent.model';
import { AgentService } from 'src/app/services/agent.service';
import { AuthService } from 'src/app/services/auth.service';
import { UpdatePassword } from 'src/app/model/update-password.model';

@Component({
  selector: 'app-agents-single-modal',
  templateUrl: './agents-single-modal.component.html',
  styleUrls: ['./agents-single-modal.component.css']
})
export class AgentsSingleModalComponent implements OnInit {

  agentForm: FormGroup;
  passwordForm: FormGroup;
  agentFullName: string;
  agentId: number;
  agentEmail: string;

  constructor(private formBuilder: FormBuilder, private agentService: AgentService, private authService: AuthService) { }

  ngOnInit() {

    this.agentForm = this.formBuilder.group({
      id: [''],
      firstName: ['', [Validators.required, Validators.maxLength(50)]],
      lastName: ['', [Validators.required, Validators.maxLength(50)]],
      email: [{value: '', disabled: 'true'}],
      businessRegistrationNumber: ['', [Validators.required, Validators.maxLength(50)]]
    });

    this.passwordForm = this.formBuilder.group({
      password1: [''],
      password2: ['']
    });

    this.getAgentById(this.authService.getEmailFromToken(localStorage.getItem('access_token')));
  }

  getAgentById(email: string) {
    this.agentService.getAgentByEmail(email).subscribe(
      (agent: Agent) => {
        this.agentForm.patchValue(agent);
        this.agentFullName = agent.firstName + ' ' + agent.lastName;
        this.agentId = agent.id;
      }
    )
  }

  onSubmit() {
    if (this.agentForm.valid) {
      this.agentService.updateAgent(this.agentForm.value as Agent).subscribe(
        (agent: Agent) => {
          this.agentFullName = agent.firstName + ' ' + agent.lastName;
        }
      );
    }
  }

  onSubmitPassword() {
    if (this.passwordForm.valid) {
      const updatePassword: UpdatePassword = {
        email: this.authService.getEmailFromToken(localStorage.getItem('access_token')),
        oldPassword: this.passwordForm.value.password1,
        newPassword: this.passwordForm.value.password2
      };

      this.agentService.changePassword(updatePassword).subscribe(
        () => {
        }
      );


    }
  }

}
