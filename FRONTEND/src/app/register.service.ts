import { Injectable } from '@angular/core';
import { RegisterDetails } from './register-details';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  studentOrTeacherRegister(registerDetails: RegisterDetails): Observable<void> {
    return this.http.post<void>('/api/register', {
      username: registerDetails.username,
      email: registerDetails.email,
      password: registerDetails.password,
      confirmationPassword: registerDetails.confirmationPassword,
      firstName: registerDetails.firstName,
      lastName: registerDetails.lastName,
      birthDate: registerDetails.birthdate,
      role: registerDetails.role
    });
  }

  companyRegister(registerDetails: RegisterDetails): Observable<void> {
    return this.http.post<void>('/api/company-register', {
      name: registerDetails.companyname,
      username: registerDetails.companyname,
      email: registerDetails.email,
      password: registerDetails.password,
      confirmationPassword: registerDetails.confirmationPassword,
      role: registerDetails.role,
      subscription: registerDetails.subscription
    });
  }
}
