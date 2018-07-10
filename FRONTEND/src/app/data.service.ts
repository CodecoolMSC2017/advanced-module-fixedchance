import { Injectable } from '@angular/core';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  user : User;

  constructor() { }

  setUser(user : User) {
    this.user = user;
  }

  getUser() {
    return this.user;
  }
}
