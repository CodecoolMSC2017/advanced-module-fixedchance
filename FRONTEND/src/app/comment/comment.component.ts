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
import { Vote } from '../vote';

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
  votes: Vote[] = [];
  voters: number[] = [];

  ngOnInit() {
    this.authService.getAuth().subscribe(resp => {
      this.user = resp;
    })
    this.route.url.subscribe(id => {
      let postId = id[1].path;
      this.fetchCurrentPost(postId).subscribe(resp => {
        this.post = resp;
        this.fetchComments();
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

  onUserNameClick(event) {
    let username = event.target.getAttribute("name");
    this.router.navigate(['users/' + username]);
  }

  onVoteClicked(event) {
    const vote = new Vote();
    vote.postId = +event.target.id;
    if (event.target.getAttribute('name') === 'true') {
      vote.vote = true;
    } else {
      vote.vote = false;
    }
    vote.voterId = this.user.user.id;
    this.getVoters().subscribe(resp => {
      this.votes = resp;
      for (let j = 0; j < this.votes.length; j++) {
        if (this.votes[j].postId === +vote.postId) {
          this.voters.push(this.votes[j].voterId);
          }
        }
        if (!this.voters.some(x => x === this.user.user.id)) {
          this.http.put<void>('/api/posts/update/'  + vote.postId + '/' + vote.vote, {}).subscribe(() => {
            if (vote.vote === true) {
              this.post.rating++;
            } else {
              this.post.rating--;
            }
            this.sendVoteToDataBase(vote);
          });
        } else {
          alert('You\'ve already voted this post!');
        }
      this.voters = [];
  });
}

  // Get the voters
  getVoters(): Observable<Vote[]> {
    return this.http.get<Vote[]>('/api/votes');
  }

  sendVoteToDataBase(vote) {
    this.http.post<void>('/api/vote', {
      'postId': vote.postId,
      'voterId': vote.voterId,
      'vote': vote.vote
      }).subscribe(resp => {});
  }
}
