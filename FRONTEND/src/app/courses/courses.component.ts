import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from '../course';
import { deserialize } from 'json-typescript-mapper';
import { HttpHeaders } from '@angular/common/http';
import { User } from '../user';
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  user : User = this.dataService.getUser();
  courses : Course[] = [];
  course : Course;

  constructor(private http : HttpClient, private dataService : DataService, private authService : AuthService) { }

  ngOnInit() {
    this.fetchCourses();
  }

  fetchCourses() {
    this.http.get<Course[]>("/api/courses").subscribe(course => {
      this.courses = course;
    });
  }

  onLogoutClick() {
    this.authService.deleteAuth();
  }
}
