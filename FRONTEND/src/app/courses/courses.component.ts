import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from '../course';
import { deserialize } from 'json-typescript-mapper';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  courses : Course[];

  constructor(private http : HttpClient) { }

  ngOnInit() {
    this.fetchCourses();
  }

  fetchCourses() {
    this.http.get<Course[]>("http://localhost:8080/courses").subscribe(response => {
      this.courses = response;
    });
    console.log(this.courses);
  }
}
