import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { DataService } from '../data.service';
import { User } from '../user';
import { AuthService } from '../auth.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.scss'],
})
export class CourseComponent implements OnInit {

  courseRating: number = 0;
  course: Course;
  teacher: User;
  userLevel: number = 0;
  currentExp: number;
  expToNextLevel: number;
  isAvailable: boolean = true;
  coursePrice: number;
  percentage: number;
  contentLoaded: boolean;
  user: User;

  constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient, private dataService: DataService, private authService: AuthService, config: NgbRatingConfig) {
    config.readonly = true;
  }

  ngOnInit() {
    this.authService.getAuth().subscribe(resp => {
      this.user = resp;
      this.route.url.subscribe(courseId => {
        this.http.get<Course>('/api/courses/' + courseId[1].path).subscribe(course => {
          this.course = course;

          for (let i = 0; i < this.course.videos.length; i++) {
            this.course.videos[i].video = this.transformUrl(this.course.videos[i].video);
          }

          this.course.students.forEach(student => {
            if (student.id === this.user.user.id) {
              this.isAvailable = false;
            }
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
          localStorage.setItem("purchaseCourse", this.course.id.toString());
          localStorage.setItem("purchaseStudent", this.user.user.id.toString());
          if (this.isAvailable === true) {
            setTimeout(() => { require('../../assets/paypal'); }, 2000);
          }
          this.contentLoaded = true;
        });
      });
    });
  }

  calculatePrice() {
    this.coursePrice = 5 + (0.6 * this.course.questions.length) + (0.5 * this.course.videos.length) + (0.3 * this.userLevel);
  }

  calculateRating() {
    let sumOfRatings = 0;
    for (let i = 0; i < this.course.reviews.length; i++) {
      sumOfRatings += this.course.reviews[i].rating;
    }
    this.courseRating = sumOfRatings / this.course.reviews.length;
  }

  transformUrl(url): string {
    const id = this.getId(url);
    return 'https://www.youtube.com/embed/' + id;
  }

  getId(url) {
    const regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
    const match = url.match(regExp);

    if (match && match[2].length === 11) {
      return match[2];
    } else {
      return 'error';
    }
  }

  onUserNameClick(event) {
    let username = event.target.getAttribute("name");
    this.router.navigate(['users/' + username]);
  }

  onCheckout() {
    
    this.http.post('api/course-student', { 'courseId': this.course.id, 'studentId': this.user.user.id })
    .subscribe(resp => { 
      this.router.navigate(['courses/' + this.course.id]);
    });
  }

  onExamClick() {
    this.router.navigate(['course-exam/' + this.course.id]);
  }
}
