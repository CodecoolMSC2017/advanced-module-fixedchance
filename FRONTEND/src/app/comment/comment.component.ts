import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { HttpClient } from '@angular/common/http'
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';
import { User } from '../user';
import { Post } from '../post';
import { Comment } from '../comment';
import { DisplayedComment } from '../displayed-comment';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, public dataService: DataService, private authService: AuthService) { }

  user : User;
  post : Post; 
  contentLoaded : boolean;
  comment : Comment;
  comments : Comment[] = [];
  correspondingComments : DisplayedComment[] = [];
  commentCont = this.commentCont;

  ngOnInit() {
    this.authService.getAuth().subscribe(resp => {
      this.user = resp;})
      this.route.url.subscribe(id => {
        let postId = id[1].path;
        this.fetchCurrentPost(id[1].path).subscribe(resp => {
          this.post = resp;
          this.contentLoaded = true;
        })
      });
  }

  fetchCurrentPost(id) : Observable<Post> {
    return this.http.get<Post>('/api/posts/' + id)
  }

  // Get the comments for this post
  fetchComments(postId){
    this.http.get<Comment[]>('/api/comments').subscribe(resp => {
      this.comments = resp;
      for(let i = 0; i < this.comments.length; i++){
        if(this.comments[i].post.id === +postId){
          let displayedComment = new DisplayedComment();
          this.getOwnerName(this.comments[i].simpleUser.id).subscribe(resp => {
            displayedComment.commentOwner = resp.username;
          });
          displayedComment.userId = this.comments[i].simpleUser.id;
          displayedComment.commentContent = this.comments[i].commentText;
          displayedComment.postId = this.comments[i].post.id;
          displayedComment.rating = this.comments[i].rating;
          this.correspondingComments.push(displayedComment);

        }
      }
    }); 
  }

  // Get the comment's owner name
  getOwnerName(simpleUserId) : Observable<User> {
    let userId = simpleUserId;
    return this.http.get<User>('/api/login/' + userId)
  }

  // Show or Hide comments
  onShowCommentsClicked(event){
    let postId = event.target.id;
    this.fetchComments(postId);
  }

  // Show or Hide the write comment form
  onWriteCommentClicked(event){
    let postId = event.target.id;
    let commentForm = document.getElementsByClassName('comment-form hidden');
    if(commentForm[0].classList.contains('hidden')){
      commentForm[0].classList.remove('hidden');
    }
    else {
      commentForm[0].classList.add('hidden');
    }
  }

  // New comment
  addComment(id) {
    console.log(id);
  }

}
