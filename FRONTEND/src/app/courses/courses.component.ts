import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from '../course';
import { deserialize } from 'json-typescript-mapper';
import { HttpHeaders } from '@angular/common/http';
import { LoginDetails } from '../login-details'
import { User } from '../user';
import { DataService } from '../data.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  user : User = this.dataService.getUser();
  courses : Course[];
  course : Course;

  constructor(private http : HttpClient, private dataService : DataService) { }

  ngOnInit() {
    this.fetchCourses();
  }

  fetchCourses() {
    this.http.get<Course>("/api/courses/1", {
      headers: new HttpHeaders ({
        'Authorization': 'Basic ' + window.btoa(this.user.username + ':' + this.user.password)
      })
    }).subscribe(course => {
      this.course = deserialize(Course, course) });
    console.log(this.course);
  }
}
