import { Component, OnInit } from '@angular/core';
import { Event } from '@angular/router';

@Component({
  selector: 'app-user-review',
  templateUrl: './user-review.component.html',
  styleUrls: ['./user-review.component.css']
})
export class UserReviewComponent implements OnInit {

  // pagination properties
  currentPage = 1;
  collectionSize = 200;
  pageSize: number;
  pageSizes: number[] = [25, 50, 100];

  constructor() { }

  ngOnInit() {
    this.pageSize = this.pageSizes[0];
  }

  changePageSize() {
    // API Call to pagable endpoint where argument is 'pageSize'
  }

}
