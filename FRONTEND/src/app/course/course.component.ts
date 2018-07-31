import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { DataService } from '../data.service';
import { User } from '../user';
import { AuthService } from '../auth.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.scss'],
})
export class CourseComponent implements OnInit {

  courseRating : number;
  course : Course;
  teacher: User;
  userLevel : number = 0;
  currentExp : number;
  expToNextLevel : number;
  isAvailable : boolean;
  coursePrice : number;
  percentage : number;

  constructor(private route : ActivatedRoute, private router : Router, private http : HttpClient, private dataService : DataService, private authService : AuthService, config : NgbRatingConfig) {
    config.readonly = true;
    this.course = this.dataService.getCurrentCourse();
    for (let i = 0; i < this.course.videos.length; i++) {
      this.course.videos[i].video = this.transformUrl(this.course.videos[i].video);
    }
  }
  
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.isAvailable = params.available;
    });

    this.teacher = this.course.teacher;
    let experience = this.course.teacher.experience;
    while (experience - 1200 - this.userLevel * 300 >= 0) {
      experience -= 1200 + this.userLevel * 300;
      this.userLevel++;
    }
    this.currentExp = experience;
    this.expToNextLevel = 1200 + this.userLevel * 300;
    this.percentage = Math.round((this.currentExp / this.expToNextLevel) * 100);
    this.calculateRating();
    this.calculatePrice();
  }

  calculatePrice() {
    this.coursePrice = 5 + (0.6 * this.course.questions.length) + (0.5 * this.course.videos.length) + (0.3 * this.userLevel);
  }

  onLogoutClick() {
    this.authService.deleteAuth();
  }

  calculateRating() {
    let sumOfRatings = 0;
    for (let i = 0; i < this.course.reviews.length; i++) {
      sumOfRatings += this.course.reviews[i].rating;
    }
    this.courseRating = sumOfRatings / this.course.reviews.length;
  }

  transformUrl(url) : string {
    let id = this.getId(url);
    return 'https://www.youtube.com/embed/' + id;
  }

  getId(url) {
    var regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
    var match = url.match(regExp);

    if (match && match[2].length == 11) {
        return match[2];
    } else {
        return 'error';
    }
  }

  onCheckout() {
    this.router.navigate(['course-checkout'], { queryParams : { price : this.coursePrice } })
  }
}
