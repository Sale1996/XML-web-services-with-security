import { CommentService } from './../../services/comment.service';
import { Component, OnInit } from '@angular/core';
import { Event } from '@angular/router';
import { Observable } from 'rxjs';
import { Comment } from 'src/app/model/comment.model';

@Component({
  selector: 'app-user-review',
  templateUrl: './user-review.component.html',
  styleUrls: ['./user-review.component.css']
})
export class UserReviewComponent implements OnInit {

  comments$: Observable<Comment[]>;

  constructor(private commentService: CommentService) { }

  ngOnInit() {
    this.getComments();
  }

  getComments() {
    this.comments$ = this.commentService.getComments();
  }

  verified(id: number) {
    this.commentService.verified(id).subscribe(() => this.getComments());
  }


}
