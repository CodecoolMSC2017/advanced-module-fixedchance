import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';
import { User } from '../user';
import { Post } from '../post';
<<<<<<< Updated upstream
import { Observable } from 'rxjs';
=======
>>>>>>> Stashed changes

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private http : HttpClient, private route: ActivatedRoute, private router: Router, public dataService : DataService) { }

  chosen: string = this.chosen;
  user : User;
  postContent : string = this.postContent;
  userName:string = this.userName;
  postedContent :string = this.postedContent;
  postTopic: string = this.postTopic;
  posts = [];
<<<<<<< Updated upstream
  searchedPosts = [];
=======
>>>>>>> Stashed changes
  post: Post;
  show : boolean = false;

  ngOnInit() {
    this.user = this.dataService.getUser();
    this.fetchPosts();
  }

  fetchPosts() {
    this.http.get<Post[]>("/api/posts").subscribe(posts => {
      this.posts = posts;
<<<<<<< Updated upstream
      this.searchedPosts = posts;
=======
      console.log(posts);
>>>>>>> Stashed changes
    });    
  }

  onLogOutClick() {
    this.router.navigate(['']);
  }

  onSearchClick() {
    this.searchedPosts = [];
    if(this.chosen.toUpperCase() === ''){
      this.searchedPosts = this.posts;
    }
    else{
      for(let i = 0; i < this.posts.length; i++) {
        if(this.posts[i].postTopic === this.chosen.toUpperCase()){
          this.searchedPosts.push(this.posts[i])
        }
      } 
    }   
  }

  onShareClick() {
<<<<<<< Updated upstream
    
  }

  addItem() {
    this.sendPost().subscribe(resp => {
      this.fetchPosts();
      this.show = true;
      this.postContent = '';
      this.postTopic = '';
    });
  }

  sendPost() : Observable<Post> {
    return this.http.post<Post>("/api/posts", {"userName": this.user.username, "postContent": this.postContent, "postTopic": this.postTopic.toUpperCase()});
=======
    this.show = true;
    this.postContent = '';
    this.postTopic = '';
  }

  addItem() {
    this.http.post<Post>("/api/posts", {"userName": this.user.username, "postContent": this.postContent, "postTopic": this.postTopic.toUpperCase()}).subscribe(resp => {console.log(resp)})
    this.posts.push(this.postContent);
    this.postContent = '';
    this.changePost(this.post);
>>>>>>> Stashed changes
  }

  removeItem(i) {
    this.posts.splice(i, 1);
    this.changePost(this.post);
  }

  changePost(post) {
    this.posts.push(post)
  }
}
