import { Trip } from './../../models/trip';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { CommentService } from './../../services/comment.service';
import { Component, Input } from '@angular/core';
import { Comment } from 'src/app/models/comment';
import { DatePipe } from '@angular/common';
import { User } from 'src/app/models/user';


@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent {


  @Input() selected: Comment | null = null;
newComment: Comment = new Comment();
editComment: Comment | null = null;
comments: Comment[] = [];
user: User = new User();
trip: Trip = new Trip();


constructor(private commentService: CommentService,
  private auth: AuthService,
  private route: ActivatedRoute,
  private router: Router,
  private datePipe: DatePipe,
  ) {
  {
  }
}


ngOnInit() {
  // let commentIdString = this.route.snapshot.paramMap.get('id');
  // if (commentIdString) {
  //   let id = parseInt(commentIdString)
  //   if(isNaN(id)) {
  //     this.router.navigateByUrl('invalidId');
  //   }
  //   else {
  //     this.commentService.show(id).subscribe({
  //       next: (comment) => {
  //         this.selected = comment;
  //       },
  //       error: (fail) => {
  //         this.router.navigateByUrl('Comment Not Found');
  //       }
  //     })
  //   }
  // }
}

displayComment(comment: Comment) {
  this.newComment = comment;
}

displayTable() {
  this.selected = null;
}

createComment(comment: Comment, tripId: number): void{


  this.auth.getLoggedInUser().subscribe(user => {
    comment.user = user;
    // this.trip.id = 1;
    comment.trip = this.trip;
    this.commentService.create(comment, tripId).subscribe({
      next:(madeComment)=>{
        this.newComment = new Comment();
        // this.reload();
      },
      error: (fail) => {
        console.error('Error creating comment');
      }
    });
  });
  }


setEditComment() {
  this.editComment = Object.assign({}, this.selected);
}

updateComment(comment: Comment, tripId: number,  goToDetail = true){
  this.commentService.update(comment, tripId).subscribe({
    next:(updatedComment)=>{
    this.editComment = null;
    if(goToDetail) {
      this.selected = updatedComment;
    }
    // this.reload();
    },
    error: (fail) => {
      console.error('Error updating comment');
      console.log(fail);
    }
  })
}

deleteComment(id: number, tripId: number){
  this.commentService.destroy(id, tripId).subscribe({
    next:(result) => {
      // this.reload();
    },
    error: (fail) => {
      console.error('Error deleting comment');
      console.error(fail);
    }
  });
}
// reload() {
//   this.commentService.index().subscribe({
//   next: (comments) => {
//     this.comments = comments;
// },
//     error: (fail) => {
//       console.error('Error getting comment list from service');
//       console.error(fail);
//     }
//   });
// }

 }
