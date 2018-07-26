import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { DataService } from '../data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe } from '@angular/common';

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
  // stores the date in the right format (yyyy-MM-dd)
  userBirthDate : string;
  xpNum : number;
  showCourse : boolean;

  courseEntries : Array<String>;

  constructor(private dataService: DataService, private route: ActivatedRoute, private router: Router, private datePipe: DatePipe) { }

  ngOnInit() {
    
    this.user = this.dataService.getUser();

    
    
    this.user.experience = 1500;
    
    // convert date to the right format
    this.userBirthDate = this.datePipe.transform(this.user.birthDate,"yyyy-MM-dd");
    
    // xp calculations
    this.userLevel = 0;
    this.calculateXp();
    
  }

  // the user can add courses if role is teacher or admin
  isValid() {
    if (this.user.role == 'TEACHER' || this.user.role == 'ADMIN') {
      return true;
    } else {
      return false;
    }
  }

  addXp() {
    this.user.experience += 100;
    this.calculateXp();
  }

  calculateXp() {
    while (this.user.experience - 1200 - this.userLevel * 300 >= 0) {
      this.user.experience -= 1200 + this.userLevel * 300;
      this.userLevel++;
    }
    this.currentExp = this.user.experience;
    this.expToNextLevel = 1200 + this.userLevel * 300;
    this.percentage = Math.round((this.currentExp / this.expToNextLevel) * 100);
  }

  showCourses(event) {
    this.showCourse = true;
    switch (event.target.id) {
      case 'programming':
      this.courseEntries = ["Python for dummies", "Python from zero to hero", "Python advanced", "Python master", "Python for web developers",
      "Java for dummies", "Java from zero to hero", "Java advanced", "Java master", "Java for web developers"];
      break;
      case 'videos':
      this.courseEntries = ["Fiery effect tutorial", "Manipulate sound effects", "Special effects", "CGI tutorial"];
      break;
      case 'photos':
      this.courseEntries = ["All about exposure", "How to change hair color", "3D effect for text tutorial", "Masking for dummies"];
      break;
      case 'softs':
      this.courseEntries = ["A way to successful job interviews", "HR questions and answers", "You can be charismatic as well!"];
      break;
    }
  }

  backToProfile() {
    this.showCourse = false;
  }
}
