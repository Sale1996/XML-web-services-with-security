import { FormBuilder, FormGroup } from '@angular/forms';
import { Message } from './../../model/message.model';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { MessageService } from 'src/app/services/message.service';
import { UserService } from 'src/app/services/user.service';
import * as moment from 'moment';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  message: Message;
  messages: Message[];

  messageObj: Message;
  reservation_id: number;

  userEmail: string;
  userLog: User;

  text: string;
  replyFormGroup: FormGroup;

  constructor(

    private messageService: MessageService,
    private authService: AuthService,
    private userService: UserService,
    private formBuilder: FormBuilder

  ) {}

  ngOnInit() {

    this.replyFormGroup = this.formBuilder.group({
      messageText: ['']
    });

    this.userEmail = this.authService.getEmailFromToken(localStorage.getItem('access_token'));
    this.getCurUser();
  }

  getCurUser() {

    this.userService.getUserByEmail(this.userEmail).subscribe(userC => {

      this.userLog = userC;
      this.getMessages(this.userLog.id);

    });
  }

  getMessages(id: number): void {

    this.messageService.getMessages(id).subscribe(message => this.messages = message);
  }

  open(message: Message) {

    this.text = message.messageBody;
    this.reservation_id = message.reservationId;

    if(!message.opened){
      this.messageService.updateMessage(message.messageId).subscribe((response) => {
     });
     message.opened = true;
    }

  }

  reply() {

    this.messageObj = {

      messageBody: this.replyFormGroup.controls['messageText'].value,
      messageTime: moment(),
      userId: this.userLog.id,
      recieved: false,
      opened: false,
      reservationId: this.reservation_id
    }

    console.log(this.messageObj);

    this.messageService.sendMessage(this.messageObj).subscribe((response) => {
      console.log('Response is: ', response);
   });

  }

}
