import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';
import { User } from '../user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private http : HttpClient, private route: ActivatedRoute, private router: Router, public dataService : DataService) { }

  choosen: string = this.choosen;
  user : User;
  postContent : string = this.postContent;
  postedContent :string = this.postedContent;
  posts = [];
  show : boolean = false;

  ngOnInit() {
    this.user = this.dataService.getUser();
    this.user.firstName = this.dataService.user.firstName;
    this.dataService.post.subscribe(res => this.posts = res);
    this.dataService.changePost(this.posts);
  }

  onLogOutClick() {
    this.router.navigate(['']);
  }

  onSearchClick() {
    console.log(this.choosen)
  }

  onShareClick() {
    this.show = true;
    this.postedContent = this.postContent;
    this.postContent = '';
  }

  addItem() {
    this.posts.push(this.postContent);
    this.postContent = '';
    this.dataService.changePost(this.posts);
  }

  removeItem(i) {
    this.posts.splice(i, 1);
    this.dataService.changePost(this.posts);
  }
}
