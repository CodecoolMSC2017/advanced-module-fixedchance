import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';
import { User } from '../user';
import { Post } from '../post';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';

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
  postTopic: string = this.postTopic;
  posts = [];
  searchedPosts = [];
  post: Post;
  show: boolean;
  contentLoaded: boolean;
  toSearch: string;

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

  onLogOutClick() {
    this.authService.deleteAuth();
  }

  onSearchClick() {
    this.searchedPosts = [];
    if (this.toSearch.toUpperCase() === '' || this.toSearch === undefined) {
      this.searchedPosts = this.posts;
    } else {
      this.posts.forEach(element => {
        if (element.postTopic.indexOf(this.toSearch.toUpperCase()) !== -1) {
          this.searchedPosts.push(element);
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

  addItem() {
    this.sendPost().subscribe(resp => {
      this.fetchPosts();
      this.show = true;
      this.postContent = '';
      this.postTopic = '';
    });
  }

  sendPost(): Observable<Post> {
    return this.http.post<Post>("/api/posts", {
      'userName': this.user.username, 'postContent': this.postContent, 'postTopic': this.postTopic.toUpperCase() });
  }

  removeItem(i) {
    this.http.delete<void>('/api/posts/' + i).subscribe(resp => { this.fetchPosts() });
  }
}
