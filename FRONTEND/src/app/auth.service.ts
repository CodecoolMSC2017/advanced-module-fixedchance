import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { User } from './user';
import { LoginDetails } from './login-details';
import { isUndefined } from 'util';
import { Company } from './company';


declare const gapi: any;

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private router: Router) { }

  private currentRole: string;

  getAuth(loginDetails?: LoginDetails): Observable<User> {
    const httpOptions = {};
    if (!isUndefined(loginDetails)) {
      httpOptions['headers'] = new HttpHeaders({
        'Authorization': 'Basic ' + window.btoa(loginDetails.username + ':' + loginDetails.password)
      });
    }
    return this.http.get<User>('/api/auth', httpOptions);
  }

  deleteAuth() {
    this.http.delete<void>('/api/auth');
  }

  getAuthCompany(loginDetails?: LoginDetails): Observable<Company> {
    const httpOptions = {};
    if (!isUndefined(loginDetails)) {
      httpOptions['headers'] = new HttpHeaders({
        'Authorization': 'Basic ' + window.btoa(loginDetails.username + ':' + loginDetails.password)
      });
    }
    return this.http.get<Company>('/api/auth/company', httpOptions);
  }

  getCurrentRole() {
    return this.currentRole;
  }

  setCurrentRole(role: string) {
    this.currentRole = role;
  }

  // logout with google acount
  signOut() {
    let auth2;
    if (gapi.auth2 !== undefined) {
    auth2 = gapi.auth2.getAuthInstance();
    }
    if (auth2 != null) {
      auth2.disconnect();
    }
  }

  onLogOutClick() {
    this.router.navigate(['']);
    this.deleteAuth();
    this.signOut();
  }
}
