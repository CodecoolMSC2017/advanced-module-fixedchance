import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private posts = new BehaviorSubject<any>(['The initial post', 'Another post']);
  post = this.posts.asObservable();

  user : User;

  constructor() { }

  changePost(post) {
    this.posts.next(post)
  }

  setUser(user : User) {
    this.user = user;
  }

  getUser() {
    return this.user;
  }
}
