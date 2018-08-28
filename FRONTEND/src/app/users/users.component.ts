import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { DataService } from '../data.service';
import { UserEntry } from '../user-entry';
import { DatePipe } from '@angular/common';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { OrderPipe } from 'ngx-order-pipe';
import { Company } from '../company';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  users: UserEntry[];
  usersProto: UserEntry[];
  user: User;
  company: Company;
  contentLoaded: boolean;
  order: string;
  cnt = 0;
  toSearch: string;

  constructor(private dataService: DataService,
    private datePipe: DatePipe,
    private authService: AuthService,
    private router: Router,
    private orderPipe: OrderPipe) {
    }

  ngOnInit() {
    this.authService.getAuth().subscribe(user => {
      this.user = user;
      if (this.user !== null) {
        this.showUsers();
      } else {
        this.authService.getAuthCompany().subscribe(company => {
          this.company = company;
          this.showUsers();
        });
      }
    });
  }

  showUsers() {
    this.dataService.fetchUsers().subscribe(users => {
      this.users = users;
      this.usersProto = this.users;
      this.users.forEach(element => {
        element.level = this.dataService.calculateLevel(element.experience);
        element.regDate = this.datePipe.transform(element.registrationDate, 'yyyy-MM-dd');
        //
        let experience = element.experience;
        while (experience - 1200 - element.level * 300 >= 0) {
          experience -= 1200 + element.level * 300;
        }
        const currentExp = experience;
        const expToNextLevel = 1200 + element.level * 300;
        element.percentage = Math.round((currentExp / expToNextLevel) * 100);
        //
        element.role = element.role.substring(5, element.role.length);
        this.contentLoaded = true;
      });
    });
  }

  onUserClicked(event) {
    const userName = event.target.id;
    if (this.user !== null && userName === this.user.user.username) {
      this.router.navigate(['profile']);
    } else {
      this.router.navigate(['users/' + userName]);
    }
  }

  onSearch() {
    this.usersProto = [];
    if (this.toSearch === '' || this.toSearch === undefined) {
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
    if (this.order === event.target.id) {
      if (this.cnt === 0) {
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
}
