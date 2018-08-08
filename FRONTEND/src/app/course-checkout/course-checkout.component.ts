import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';
import { User } from '../user';
import { Course } from '../course';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-course-checkout',
  templateUrl: './course-checkout.component.html',
  styleUrls: ['./course-checkout.component.css']
})
export class CourseCheckoutComponent implements OnInit {

  price: number;
  coursePrice: number;
  errormessage: string;
  user: User;
  course: Course;
  userLevel: number;
  teacher: User;

  constructor(private authService: AuthService, private dataService: DataService, private http: HttpClient, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.url.subscribe(uri => {
      this.http.get<Course>('/api/courses/' + uri[1].path).subscribe(course => {
        this.course = course;
        this.authService.getAuth().subscribe(resp => {
          this.user = resp;
          this.teacher = this.course.teacher;
          let experience = this.course.teacher.experience;
          while (experience - 1200 - this.userLevel * 300 >= 0) {
            experience -= 1200 + this.userLevel * 300;
            this.userLevel++;
          }
          this.coursePrice = 5 + (0.6 * this.course.questions.length) + (0.5 * this.course.videos.length) + (0.3 * this.userLevel);
        });
      });
    });
  }

  onSubmit() {
    if (this.price >= this.coursePrice) {
      this.errormessage = 'Successful. Redirecting you to the course-page';
      this.http.post('api/course-student', { 'courseId': this.course.id, 'studentId': this.user.user.id })
        .subscribe((resp => { }));
      setTimeout(() => { this.router.navigate(['course']), 2000 });
    } else {
      this.errormessage = 'Confirmation price must be equal or higher than minimum price';
    }
  }

  onBack() {
    this.router.navigate(['course/' + this.course.id]);
  }
}
