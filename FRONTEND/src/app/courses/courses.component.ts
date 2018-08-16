import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from '../course';
import { User } from '../user';
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { log } from 'util';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  user: User;
  courses: Course[] = [];
  course: Course;
  userLevel: number;
  availableCount : number = 0;
  purchasedCount : number = 0;
  contentLoaded : boolean;

  constructor(private http: HttpClient, private dataService: DataService, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.authService.getAuth().subscribe(resp => {
      this.user = resp;
      this.fetchCourses().subscribe(courses => {
        this.contentLoaded = true;
      });
    });
  }

  fetchCourses() : Observable<Course[]> {
    let x = this.http.get<Course[]>('/api/courses');
      x.subscribe(courses => {
      this.courses = courses;
      this.countAvailables();
      for (let i = 0; i < this.courses.length; i++) {
        this.courses[i].teacherLevel = this.dataService.calculateLevel(this.courses[i].teacher.experience);
        this.courses[i].rating = this.getRating(this.courses[i].reviews);
      }
    });
    return x;
  }

  countAvailables() {
    for (let i = 0; i < this.courses.length; i++) {
      if (!this.checkForStudent(this.courses[i])) {
        this.availableCount++;
      } else {
        this.purchasedCount++;
      }

    }
  }

  getRating(reviews): number {
    let sum = 0;
    reviews.forEach(element => {
      sum += element.rating;
    });
    return sum / reviews.length;
  }

  onCourseClick(event) {
    this.router.navigate(['courses/' + event.target.id]);
  }

  findCourseById(id): Course {
    for (let i = 0; i < this.courses.length; i++) {
      if (this.courses[i].id === id) {
        return this.courses[i];
      }
    }
  }

  checkForStudent(course) {
    for (let i = 0; i < course.students.length; i++) {
      if (course.students[i].id === this.user.user.id) {
        return true;
      }
    }
    return false;
  }
}
