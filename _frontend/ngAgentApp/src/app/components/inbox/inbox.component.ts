import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageModalComponent } from './message-modal/message-modal.component';
import { Message } from 'src/app/model/message.model';
import { Observable } from 'rxjs';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  messages$: Observable<Message[]>;

  constructor(private modalService: NgbModal, private messageService: MessageService) { }

  ngOnInit() {
    this.getMessages();
  }

  getMessages() {
    this.messages$ = this.messageService.getMessages(parseInt(localStorage.getItem('accommodationId')));
  }

  openMessageModal(message: Message) {
    const agentModalRef = this.modalService.open(MessageModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop',
        backdrop: 'static'
      });
    agentModalRef.componentInstance.messageInput = message;
    agentModalRef.componentInstance.messageEmiter.subscribe(
      (data) => {
        this.getMessages();
      }
    );


  }

}
