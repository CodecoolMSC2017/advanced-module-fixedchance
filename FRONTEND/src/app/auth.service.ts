import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { User } from './user';
import { LoginDetails } from './login-details';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private router : Router) { }

  getAuth(loginDetails: LoginDetails): Observable<User> {
    return this.http.get<User>('/api/auth', {
      headers: new HttpHeaders({
        'Authorization': 'Basic ' + window.btoa(loginDetails.username + ':' + loginDetails.password)
      })
    });
  }

  deleteAuth() {
    this.http.delete<void>('/api/auth');
    this.router.navigate([""]);
  }
}