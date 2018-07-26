import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { DataService } from '../data.service';
import { User } from '../user';
import { AuthService } from '../auth.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.scss'],
})
export class CourseComponent implements OnInit {

  courseRating : number;
  course : Course;
  teacher: User;
  userLevel : number;
  currentExp : number;
  expToNextLevel : number;

  constructor(private dataService : DataService, private authService : AuthService, config : NgbRatingConfig) {
    config.readonly = true;
   }

  ngOnInit() {
    this.course = this.dataService.getCurrentCourse();
    this.teacher = this.course.teacher;
    this.userLevel = 0;
    this.teacher.experience = 3600;
    while (this.teacher.experience - 1200 - this.userLevel * 300 >= 0) {
      this.teacher.experience -= 1200 + this.userLevel * 300;
      this.userLevel++;
    }
    this.currentExp = this.teacher.experience;
    this.expToNextLevel = 1200 + this.userLevel * 300;
    this.calculateRating();
  }

  onLogoutClick() {
    this.authService.deleteAuth();
  }

  calculateRating() {
    let sumOfRatings = 0;
    for (let i = 0; i < this.course.reviews.length; i++) {
      sumOfRatings += this.course.reviews[i].rating;
    }
    this.courseRating = Math.round((13 / 3) * 100) / 100;
    //this.courseRating = sumOfRatings / this.course.reviews.length;
    console.log(this.courseRating);
  }
}
