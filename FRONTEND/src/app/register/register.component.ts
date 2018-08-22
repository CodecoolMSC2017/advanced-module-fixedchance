import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from '../message';
import { RegisterService } from '../register.service';
import { RegisterDetails } from '../register-details';

@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerDetails: RegisterDetails = new RegisterDetails();
  name: string = this.name;
  message: string;

  selectedRole: string;
  prevSelectedRole: Element;

  selectedSub: string;
  prevSelectedSub: Element;

  constructor(private registerService: RegisterService, private http: HttpClient, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
  }

  onRegisterClick() {
    if (this.checkForMissingInfo()) {
      return;
    }
    if (!this.isValidBirthday(new Date(this.registerDetails.birthdate))) {
      return;
    }
    if (this.selectedRole === 'STUDENT' || this.selectedRole === 'TEACHER') {
      this.register('REGULAR');
    } else {
      this.register('COMPANY');
    }
  }

  roleChosen(event) {
    if (this.prevSelectedRole != null) {
      this.prevSelectedRole.classList.remove('activerole');
    }
    this.prevSelectedRole = event.target;
    event.target.classList.add('activerole');
    this.selectedRole = event.target.name;
  }

  subChosen(event) {
    if (this.prevSelectedSub != null) {
      this.prevSelectedSub.classList.remove('activesub');
    }
    this.prevSelectedSub = event.target;
    event.target.classList.add('activesub');
    this.selectedSub = event.target.name;
  }

  checkForMissingInfo() {
    if (this.selectedRole === 'STUDENT' || this.selectedRole === 'TEACHER') {
      if (this.registerDetails.email == null || this.registerDetails.password == null
        || this.registerDetails.firstName == null || this.registerDetails.lastName == null || this.registerDetails.username == null ||
        this.registerDetails.birthdate == null) {
        return true;
      }
    } else {
      if (this.registerDetails.email == null || this.registerDetails.companyname == null || this.registerDetails.password == null) {
        return true;
      }
    }

    if (this.registerDetails.password !== this.registerDetails.confirmationPassword) {
      return true;
    }
    return false;
  }

  isValidBirthday(birthdate: Date) {
    if (birthdate < new Date('1899-01-01') || birthdate > new Date('2018-01-01')) {
      this.message = 'Incorrect birthdate';
      return false;
    }
    return true;
  }

  redirectAfterSuccess() {
    this.message = 'Your registration was successful!';
    setTimeout(() => {
      location.reload();
    }, 1000);
  }

  register(role: string) {
    if (role === 'REGULAR') {
      this.registerDetails.role = this.selectedRole;
      this.registerService.studentOrTeacherRegister(this.registerDetails).subscribe(user => {
        this.redirectAfterSuccess();
      }, error => this.message = error.error.message);
    }
    if (role === 'COMPANY') {
      this.registerDetails.role = this.selectedRole;
      this.registerDetails.subscription = this.selectedSub;
      this.registerService.companyRegister(this.registerDetails).subscribe(user => {
        this.redirectAfterSuccess();
      }, error => this.message = error.error.message);
    }
  }
}
