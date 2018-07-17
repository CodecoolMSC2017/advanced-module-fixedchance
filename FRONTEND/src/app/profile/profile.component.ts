import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { DataService } from '../data.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  userLevel : number;
  currentExp : number;
  expToNextLevel : number;
  user : User;
  percentage : number;

  constructor(private dataService : DataService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    //this.user = this.dataService.user;
    
    this.user = new User();
    this.user.experience = 2700;
    this.user.email = 'hegedus.csanad96@gmail.com';
    this.user.firstName = 'Csanád';
    this.user.lastName = 'Hegedűs';
    this.user.birthDate = new Date('1996-11-12');
    this.user.role = 'ADMIN';

    if (this.user.experience < 1200) {
      this.userLevel = 0;
      this.currentExp = this.user.experience;
      this.expToNextLevel = 1200;
      this.percentage = Math.round((this.currentExp / 1200) * 100);
    } else {
      this.user.experience -= 1200;
      this.userLevel = 1;
      while (this.user.experience - 1200 - this.userLevel * 300 >= 0) {
        this.userLevel++;
        this.user.experience -= 1200 - this.userLevel * 300;
      }
      this.currentExp = this.user.experience;
      this.expToNextLevel = 1200 + this.userLevel * 300;
      this.percentage = Math.round((this.currentExp / this.expToNextLevel) * 100);
    }
  }
}
