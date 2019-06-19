import { FormBuilder, FormGroup } from '@angular/forms';
import { Message } from './../../model/message.model';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { MessageService } from 'src/app/services/message.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  message: Message;
  messages: Message[];

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
      message: ['']
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

  }

  reply() {


  }

}
