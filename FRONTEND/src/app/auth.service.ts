import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { User } from './user';
import { LoginDetails } from './login-details';
import { isUndefined } from 'util';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private router: Router) { }

  getAuth(loginDetails?: LoginDetails): Observable<User> {
    const httpOptions = {};
    if (!isUndefined(loginDetails)) {
      httpOptions['headers'] = new HttpHeaders({
          'Authorization': 'Basic ' + window.btoa(loginDetails.username + ':' + loginDetails.password)
      });
    }
    return this.http.get<User>('/api/auth', httpOptions);
  }

  deleteAuth(): Observable<void> {
    return this.http.delete<void>('/api/auth');
  }
}
