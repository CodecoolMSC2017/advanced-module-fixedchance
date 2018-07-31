import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from '../course';
import { User } from '../user';
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { log } from 'util';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  user : User = this.dataService.getUser();
  courses : Course[] = [];
  course : Course;
  userLevel : number;

  constructor(private http : HttpClient, private dataService : DataService, private authService : AuthService, private router : Router) { }

  ngOnInit() {
    this.fetchCourses();
  }

  fetchCourses() {
    this.http.get<Course[]>("/api/courses").subscribe(courses => {
      this.courses = courses;
      console.log(this.courses[0]);
      courses[0].setTeacherLevel(2);
      console.log(courses[0].teacherLevel);
      for (let i = 0; i < this.courses.length; i++) {
        this.courses[i].setTeacherLevel(this.dataService.calculateLevel(this.courses[i].teacher));
        console.log(this.courses[i]);
        console.log(this.courses[i].teacher);
      }
    });
  }

  onLogoutClick() {
    this.authService.deleteAuth();
  }

  onCourseClick(event) {
    this.dataService.setCurrentCourse(this.findCourseById(event.target.id));
    this.router.navigate(['course'], { queryParams : { available : event.target.getAttribute('name')} });
  }

  findCourseById(id) : Course {
    for (let i = 0; i < this.courses.length; i++) {
      if (this.courses[i].id == id) {
        return this.courses[i];
      }
    }
  }

  checkForStudent(course) {
    for (let i = 0; i < course.students.length; i++) {
      if (course.students[i].id == this.user.id) {
        return true;
      }
    }
    return false;
  }
}
