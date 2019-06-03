import { Component, OnInit } from '@angular/core';
import { Event } from '@angular/router';
import { Observable } from 'rxjs';
import { CommentService } from 'src/app/services/comment.service';

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
  comments$: Observable<Comment[]>;

  constructor(private commentService: CommentService) { }

  ngOnInit() {
    this.pageSize = this.pageSizes[0];
    this.getComments();
  }

  getComments() {
    this.comments$ = this.commentService.getComments();
  }

  changePageSize() {
    // API Call to pagable endpoint where argument is 'pageSize'
  }

}
