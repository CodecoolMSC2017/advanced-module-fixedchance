import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DataService } from '../data.service';
import { User } from '../user';
import { Post } from '../post';
import { Vote } from '../vote';
import { NewPost } from '../new-post';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';

declare const gapi: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, public dataService: DataService, private authService: AuthService) { }

  chosen: string = this.chosen;
  user: User;
  postContent: string = this.postContent;
  userName: string = this.userName;
  postedContent: string = this.postedContent;
  topics: string[] = [];
  currentTopic: string;
  posts = [];
  searchedPosts = [];
  post: NewPost = new NewPost();
  show: boolean;
  contentLoaded: boolean;
  toSearch: string;
  rating: number;
  users: string[];
  vote: Vote = new Vote;

  ngOnInit() {
    this.authService.getAuth().subscribe(resp => {
      this.user = resp;
      this.fetchPosts();
      this.contentLoaded = true;
    });
  }

  fetchPosts() {
    this.http.get<Post[]>('/api/posts').subscribe(posts => {
      this.posts = posts;
      this.searchedPosts = posts;
    });
  }


  onSearchClick() {
    this.searchedPosts = [];
    if (this.toSearch.toUpperCase() === '' || this.toSearch === undefined) {
      this.searchedPosts = this.posts;
    } else {
      this.posts.forEach(element => {
        for(let i=0; i < element.topics.length; i++){
          if (element.topics[i].name.toUpperCase() === (this.toSearch.toUpperCase())){
            this.searchedPosts.push(element);
          }
        }
      });
    }
  }

  onUserClicked(event) {
    let userName = event.target.id;
    if (userName == this.user.username) {
      this.router.navigate(['profile']);
    } else {
      this.router.navigate(['users/' + userName]);
    }
  }

  topicKey(event) {
    if (event.key === ' ') {
      if (this.currentTopic !== ' ' && this.currentTopic != null) {
        if (!this.post.topics.some(x => x === this.currentTopic) && this.post.topics.length < 10) {
          this.post.topics.push(this.currentTopic);
        }
      }
      this.currentTopic = '';
    }
  }

  removeTopic(event) {
    let topicToRemove = event.target.id;
    for (let i = 0; i < this.post.topics.length; i++) {
      if (this.post.topics[i] === topicToRemove) {
        this.post.topics.splice(i, 1);
      }
    }
  }

  // Add new post
  addItem() {
    if (this.post.topics.length >= 3 && this.post.topics.length <= 10) {
      this.sendPost().subscribe(post => {
        let postId = post.id;
        this.savePostTopic(postId).subscribe(resp => {
          this.fetchPosts()})
          this.show = true;
          this.postContent = '';
          this.post.topics = [];
        });
      } else if (this.post.topics.length < 3) {
        alert("Please provide at least 3 topics");
      } else {
        alert("Please provide less than 10 topics");
      }
    }

  sendPost(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json;charset=UTF-8'
    });
    const options = { headers: headers };
    return this.http.post("/api/posts", { 'userName': this.user.user.username, 'postContent': this.postContent, 'rating' : 0 }, options);
  }

  savePostTopic(postId): Observable<any>{
    let x;
    for(let i = 0;i < this.post.topics.length; i++){
      x = this.http.post<void>("/api/post-topics/" + postId, { 'id': postId, 'name': this.post.topics[i] }); 
    }
    return x;
  }

  // Remove a specific post
  removeItem(i) {
    this.http.delete<void>('/api/posts/' + i).subscribe(resp => { this.fetchPosts() });
  }

  // Navigate to a specific post to write comment
  fetchPost(id) {
    this.http.get<Post>('/api/posts/' + id).subscribe(resp => {
      this.post = resp;
    });
  }

  onPostClicked(id) {
    console.log(id);
    this.router.navigate(['comment/' + id]);
  }

  // logout with google acount
 signOut() {
  const auth2 = gapi.auth2.getAuthInstance();
  if (auth2 != null) {
          auth2.disconnect();
          }
  }

  onUpVoteClicked(event) {
    let postId = +event.target.id;
    let userId = +this.user.user.id;
    let currentVote = true;
    this.vote.postId = postId;
    this.vote.voterId = userId;
    this.vote.vote = currentVote;
    let postUsers = [];
    for(let i = 0; i < this.posts.length; i++){
      if(postId === this.posts[i].id){
        for(let j = 0; j < this.posts[i].users.length; j++){
          postUsers.push(this.posts[i].users[j].username);
        }
        if(!postUsers.some(x => x === this.user.user.username)){
          this.http.post<void>('/api/posts/update/up/' + postId, {}).subscribe(resp => {this.sendVoteToDataBase(this.vote)})
        }
        else{
          alert("You've already voted this post!")
        }
      }
    }
  }

  onDownVoteClicked(event){
    let postId = +event.target.id;
    let userId = +this.user.user.id;
    let currentVote = false;
    this.vote.postId = postId;
    this.vote.voterId = userId;
    this.vote.vote = currentVote;
    let postUsers = [];
    for(let i = 0; i < this.posts.length; i++){
      if(postId === this.posts[i].id){
        for(let j = 0; j < this.posts[i].users.length; j++){
          postUsers.push(this.posts[i].users[j].username);
        }
        if(!postUsers.some(x => x === this.user.user.username)){
          this.http.post<void>('/api/posts/update/down/' + postId, {}).subscribe(resp => {this.sendVoteToDataBase(this.vote)})
        }
        else{
          alert("You've already voted this post!")
        }
      }
    }
  }

  sendVoteToDataBase(vote) {
    this.http.post<void>('/api/vote', {'postId': vote.postId, 'voterId': vote.voterId, 'vote': vote.vote }).subscribe(resp => {this.fetchPosts()})
  }
}
