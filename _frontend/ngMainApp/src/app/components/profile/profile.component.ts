import { Location } from '@angular/common';
import { Message } from './../../model/message.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  selected = 0;
  hovered = 0;
  readonly = false;
  messageForm: FormGroup;
  messageObj: Message = new Message();

  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private location: Location
  ) { }

  ngOnInit() {
    this.messageForm = this.formBuilder.group({
      id: [''],
      password: [''],
      body: ['']
    });

  }

  onSubmit() {
    // if (this.messageForm.valid) {
    //   this.messageService.sendMessage(this.messageService.value as User).subscribe(
    //     (user: User) => console.log(user)
    //   );
    // }

    this.messageObj.messageBody = this.messageForm.controls['body'].value;
    this.messageObj.recieved = false; // ovo je poslana poruka (od strane klijenta), a ne primljena
    this.messageObj.opened = false;

    console.log('AAAAAAAAAAAAAAAAAAAAAAA: ', this.messageObj.messageBody);  // ZASTO OVO IGNORISE

    this.messageService.sendMessage(this.messageObj).subscribe((response) => {
      console.log('Response is: ', this.messageObj.messageBody);  // ZASTO OVO IGNORISE
      this.location.back();
   },
   (error) => {
      // catch the error
      console.error('An error occurred, ', error);
   });
  }
}
