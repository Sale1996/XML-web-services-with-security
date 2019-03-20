import { Component, OnInit } from '@angular/core';
import { Request } from 'src/app/model/request.model';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  request: Request;

  constructor() { }

  ngOnInit() {

    this.request = {
      name: 'Marko',
      publicKey: '6546213678',
      country: 'Srbija',
      city: 'Kosjeric',
      state: '31260',
      organization: 'Marko & Sinovi',
      organizationUnit: 'IT'
    };
  }

}
