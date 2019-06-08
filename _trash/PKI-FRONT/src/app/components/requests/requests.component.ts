import { Component, OnInit } from '@angular/core';
import { Request } from 'src/app/model/request.model';
import { Observable } from 'rxjs';
import { share } from 'rxjs/operators';
import { RequestService } from 'src/app/services/request.service';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  requests: Observable<Request[]>;

  constructor(
    private requestService: RequestService
  ) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.requests = this.requestService.getAllRequests().pipe(share());
  }

}
