import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageModalComponent } from './message-modal/message-modal.component';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  // pagination properties
  currentPage = 1;
  collectionSize = 200;
  pageSize: number;
  pageSizes: number[] = [25, 50, 100];

  constructor(private modalService: NgbModal) { }

  ngOnInit() {
    this.pageSize = this.pageSizes[0];
  }


  openMessageModal(id: number) {
    const agentModalRef = this.modalService.open(MessageModalComponent,
      {
        size: 'lg',
        centered: true,
        backdropClass: 'custom-modal-backdrop',
        backdrop: 'static'
      });
    agentModalRef.componentInstance.id = id;
    agentModalRef.componentInstance.messageEmiter.subscribe();


  }

}
