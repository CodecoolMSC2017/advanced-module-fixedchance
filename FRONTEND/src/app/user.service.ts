import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from './user';
import { NewUser } from './new-user';
import { PasswordChange } from './password-change';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // stores the profile picture from google
  userPicture: String;

  constructor(private http: HttpClient, private user: User) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users');
  }

  changePassword(passwordChange: PasswordChange): Observable<void> {
    return this.http.post<void>('/api/users/change-password', {
      oldPassword: passwordChange.oldPassword,
      newPassword: passwordChange.newPassword,
      confirmationPassword: passwordChange.confirmationPassword
    });
  }

  addUser(newUser: NewUser): Observable<User> {
    return this.http.post<User>('/api/users', {
      username: newUser.username,
      password: newUser.password,
      confirmationPassword: newUser.confirmationPassword
    });
  }

  setUserPicture(pictureUrl: String) {
    this.userPicture = pictureUrl;
  }

  getUserPicture() {
    return this.userPicture;
  }
}
