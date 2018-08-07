import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { User } from './user';
import { Course } from './course';
import { Post } from './post';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AddCourse } from './add-course';
import { UserEntry } from './user-entry';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  user : User;

  course : Course;
  demoCourse : AddCourse;

  post: Post;

  constructor(private http : HttpClient) { }

  setUser(user : User) {
    this.user = user;
  }

  getUser() {
    return this.user;
  }

  calculateLevel(experience) : number {
    let userLevel = 0;
    while (experience - 1200 - userLevel * 300 >= 0) {
      experience -= 1200 + userLevel * 300;
      userLevel++;
    }
    return userLevel;
  }

  fetchStudentCourses(id) : Observable<Course[]> {
    return this.http.get<Course[]>("/api/courses/student/" + id);
  }

  fetchTeacherCourses(id) : Observable<Course[]> {
    return this.http.get<Course[]>("/api/courses/" + id + "/courses");
  }

  fetchUsers() : Observable<UserEntry[]> {
    return this.http.get<UserEntry[]>("/api/all-users");
  }

  getGuestUser(username) : Observable<UserEntry> {
    return this.http.get<UserEntry>("/api/users/" + username);
  }

  fetchCurrentCourse(courseId) : Observable<Course> {
    return this.http.get<Course>("/api/courses/"+courseId);
  }
}
