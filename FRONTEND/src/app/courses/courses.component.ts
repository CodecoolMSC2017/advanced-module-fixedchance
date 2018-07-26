import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from '../course';
import { deserialize } from 'json-typescript-mapper';
import { HttpHeaders } from '@angular/common/http';
import { User } from '../user';
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  user : User = this.dataService.getUser();
  courses : Course[] = [];
  course : Course;

  constructor(private http : HttpClient, private dataService : DataService, private authService : AuthService, private router : Router) { }

  ngOnInit() {
    this.fetchCourses();
  }

  fetchCourses() {
    this.http.get<Course[]>("/api/courses").subscribe(courses => {
      this.courses = courses;
    });
  }

  onLogoutClick() {
    this.authService.deleteAuth();
  }

  onCourseClick(event) {
    this.dataService.setCurrentCourse(this.findCourseById(event.target.id));
    this.router.navigate(['course']);
  }

  findCourseById(id) : Course {
    for (let i = 0; i < this.courses.length; i++) {
      if (this.courses[i].id == id) {
        return this.courses[i];
      }
    }
  }
}
