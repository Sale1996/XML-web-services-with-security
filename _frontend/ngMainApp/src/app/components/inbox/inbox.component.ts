import { Component, OnInit } from '@angular/core';
import { MessageService } from 'src/app/services/message.service';
import { Message } from 'src/app/model/message.model';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  message: Message;
  messages: Message[];

  constructor(

    private messageService: MessageService

  ) {}

  ngOnInit() {

    this.getMessages();
  }

  getMessages(): void {
    this.messageService.getMessages().subscribe(message => this.messages = message);
  }

}
