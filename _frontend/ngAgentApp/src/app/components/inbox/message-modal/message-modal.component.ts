import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Message } from 'src/app/model/message.model';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-message-modal',
  templateUrl: './message-modal.component.html',
  styleUrls: ['./message-modal.component.css']
})
export class MessageModalComponent implements OnInit {

  @Input() messageInput?: Message;
  @Output() messageEmiter: EventEmitter<any> = new EventEmitter();
  messageForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder, private messageService: MessageService) { }

  ngOnInit() {

    this.messageForm = this.formBuilder.group({
      id: [''],
      message: ['', Validators.required]
    });

    if (!this.messageInput.opened) {
      this.updateMessageOpened();
    }
  }


  updateMessageOpened() {
    this.messageInput.opened = true;
    this.messageService.updateMessage(this.messageInput).subscribe(() => { });
  }


  onSubmit() {
    if (this.messageForm.valid) {
      var message: Message = {
        messageId: -1,
        messageBody: this.messageForm.value.message,
        opened: false,
        recieved: true,
        userId: this.messageInput.userId,
        reservationId: this.messageInput.reservationId,
        messageTime: ''
      }

      this.messageService.createMessage(message).subscribe(
        () => {
          this.messageEmiter.emit(this.messageForm.value); //ovde treba this.messageForm as Message...gde je message klasa
        }
      );


      this.activeModal.close();
    }
  }

}
