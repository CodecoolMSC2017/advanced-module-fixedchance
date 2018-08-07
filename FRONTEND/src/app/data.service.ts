import { Injectable } from '@angular/core';
import { Course } from './course';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserEntry } from './user-entry';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http : HttpClient) { }

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
