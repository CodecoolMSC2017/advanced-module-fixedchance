import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { User } from './user';
import { Course } from './course';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private posts = new BehaviorSubject<any>(['Importing JSON file from Amazon with Python: https://stackoverflow.com/questions/51436740/importing-json-file-from-amazon-s3-into-aws-rds-postgresql-using-python', 'Creating own tag in HTML: https://stackoverflow.com/questions/51414777/can-we-make-our-own-tag-in-html']);
  post = this.posts.asObservable();

  user : User;

  course : Course;

  constructor(private http : HttpClient) { }

  changePost(post) {
    this.posts.next(post)
  }

  setUser(user : User) {
    this.user = user;
  }

  getUser() {
    return this.user;
  }

  setCurrentCourse(course) {
    this.course = course;
  }

  getCurrentCourse() {
    return this.course;
  }

  calculateLevel(experience) : number {
    let userLevel = 0;
    while (experience - 1200 - userLevel * 300 >= 0) {
      experience -= 1200 + userLevel * 300;
      userLevel++;
    }
    return userLevel;
  }
}
