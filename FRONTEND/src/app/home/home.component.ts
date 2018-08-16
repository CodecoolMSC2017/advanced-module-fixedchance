import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';
import { User } from '../user';
import { Post } from '../post';
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
        console.log(this.post);
        console.log(this.post.topics);
        if (!this.post.topics.some(x => x === this.currentTopic) && this.post.topics.length <= 10) {
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
    this.sendPost().subscribe(post => {
      let postId = post.id;
      this.savePostTopic(postId).subscribe(resp => {
        this.fetchPosts()})
        this.show = true;
        this.postContent = '';
        this.post.topics = [];
      });
  }

  sendPost(): Observable<any> {
    let x;
    x = this.http.post("/api/posts", {
      'userName': this.user.user.username, 'postContent': this.postContent })
    return x;
  }

  savePostTopic(postId): Observable<any>{
    let x;
    for(let i = 0;i < this.post.topics.length; i++){
      x = this.http.post<void>("/api/post-topics/" + postId, {
        'id': postId, 'name': this.post.topics[i]});
      }
    return x;
  }

  // Remove a specific post
  removeItem(i) {
    this.http.delete<void>('/api/posts/' + i).subscribe(resp => { this.fetchPosts() });
  }

  //Write comment
  onPostClicked($event) {
    console.log(event.target);
  }

  // logout with google acount
 signOut() {
  const auth2 = gapi.auth2.getAuthInstance();
  if (auth2 != null) {
          auth2.disconnect();
          }
  }

  onUpVoteClicked(event){
    let postId = +event.target.id;
    for(let i = 0; i < this.posts.length; i++){
      if(postId === this.posts[i].id){
        this.http.post<void>('/api/posts/update/up/' + postId, {}).subscribe(resp => {this.fetchPosts()})
      }
    }
  }

  onDownVoteClicked(event){
    let postId = +event.target.id;
    for(let i = 0; i < this.posts.length; i++){
      if(postId === this.posts[i].id){
        this.http.post<void>('/api/posts/update/down/' + postId, {}).subscribe(resp => {this.fetchPosts()})
      }
    }
  }
}
