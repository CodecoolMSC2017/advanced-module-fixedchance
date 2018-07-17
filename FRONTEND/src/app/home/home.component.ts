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

  ngOnInit() {
    this.user = this.dataService.user;
    this.user.firstName = this.dataService.user.firstName;
  }

  onLogOutClick() {
    this.router.navigate(['']);
  }

  onSearchClick() {
    console.log(this.choosen)
  }

  onShareClick() {
    console.log(this.postContent); 
  }
}
