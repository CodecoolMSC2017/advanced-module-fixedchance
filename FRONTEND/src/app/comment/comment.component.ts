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

  user: User;
  post: Post;
  contentLoaded: boolean;
  correspondingComments: DisplayedComment[] = [];
  commentCont = this.commentCont;
  comments: boolean;

  ngOnInit() {
    this.authService.getAuth().subscribe(resp => {
      this.user = resp;
    })
    this.route.url.subscribe(id => {
      let postId = id[1].path;
      this.fetchCurrentPost(postId).subscribe(resp => {
        this.post = resp;
        this.fetchComments();
        console.log(this.post);
        this.contentLoaded = true;
      })
    });
  }

  fetchCurrentPost(id): Observable<Post> {
    return this.http.get<Post>('/api/posts/' + id);
  }

  // Get the comments for this post
  fetchComments() {
    for (let i = 0; i < this.post.comments.length; i++) {
      this.getOwnerName(this.post.comments[i].userId).subscribe(resp => {
        this.post.comments[i].username = resp.username;
      });
    }
  }

  // Get the comment's owner name
  getOwnerName(simpleUserId): Observable<User> {
    let userId = simpleUserId;
    return this.http.get<User>('/api/login/' + userId)
  }

  // New comment
  addComment(id) {
    this.http.post<void>("/api/comments/" + id, { "userId": this.user.user.id, "postId": id, "commentText": this.commentCont, "rating": 0 }).subscribe(resp => {
      this.ngOnInit();
    });
  }

<<<<<<< HEAD
  // UpVote
  onUpVoteClicked(event) {
    let commentId = event.target.id;
=======
  onUserNameClick(event) {
    let username = event.target.getAttribute("name");
    this.router.navigate(['users/' + username]);
>>>>>>> 626dd28eec20aaf42c824317d15808adf58fd367
  }
}
