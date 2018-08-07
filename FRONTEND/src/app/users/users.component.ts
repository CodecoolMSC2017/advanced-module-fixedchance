import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { DataService } from '../data.service';
import { UserEntry } from '../user-entry';
import { DatePipe } from '@angular/common';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { OrderPipe } from 'ngx-order-pipe';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  users : UserEntry[];
  usersProto : UserEntry[];
  user : User;
  contentLoaded : boolean = false;
  order : string;
  cnt : number = 0;
  toSearch : string;

  constructor(private dataService : DataService, private datePipe: DatePipe, private authService : AuthService, private router : Router, private orderPipe: OrderPipe) { }

  ngOnInit() {
    this.user = this.dataService.getUser();
    this.dataService.fetchUsers().subscribe(users => {
      this.users = users;
      this.usersProto = this.users;
      let currentExp;
      let expToNextLevel;
      this.users.forEach(element => {
        element.level = this.dataService.calculateLevel(element.experience);
        element.regDate = this.datePipe.transform(element.registrationDate,"yyyy-MM-dd");
        element.percentage = Math.round(element.experience / (1200 + element.level * 300)) * 10;
        element.role = element.role.substring(5, element.role.length);
      });
      this.contentLoaded = true});
  }

  onUserClicked(event) {
    let userName = event.target.id;
    if (userName == this.user.username) {
      this.router.navigate(['profile']);
    } else {
      this.router.navigate(['users/'+userName]);
    }
  }

  onSearch() {
    this.usersProto = [];
    if (this.toSearch === '' || this.toSearch == undefined) {
      this.usersProto = this.users;
    } else {
      this.users.forEach(element => {
        if (element.userName.indexOf(this.toSearch) !== -1) {
          this.usersProto.push(element);
        }
      });
    }
  }

  onReorder(event) {
    if (this.order == event.target.id) {
      if (this.cnt == 0) {
        this.usersProto = this.orderPipe.transform(this.usersProto, this.order, true);
        this.cnt--;
      } else {
        this.usersProto = this.orderPipe.transform(this.usersProto, this.order);
        this.cnt++;  
      }
    } else {
      this.order = event.target.id;
      this.cnt = 0;
      this.usersProto = this.orderPipe.transform(this.usersProto, this.order);
    }
  }

  onLogoutClick() {
    this.authService.deleteAuth();
  }
}
