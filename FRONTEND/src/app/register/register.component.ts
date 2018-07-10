import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from '../message';
import { deserialize } from 'json-typescript-mapper';

@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  email : string = this.email;
  password : string = this.password;
  confpassword : string = this.confpassword;
  firstname : string = this.firstname;
  lastname : string = this.lastname;
  birthdate : Date = this.birthdate;
  name : string = this.name;

  message : Message;
  errormessage: string;
  selectedRole : string;
  prevSelectedRole : Element;

  selectedSub : string;
  prevSelectedSub : Element;


  constructor(private http : HttpClient, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
  }

  onRegisterClick() {
    if (this.checkForMissingInfo()) {
      return;
    }

    let post;
    if (this.selectedRole == 'STUDENT' || this.selectedRole == 'TEACHER') {
      const params = {email : this.email, password : this.password, confpassword : this.confpassword,
      firstName : this.firstname, lastName : this.lastname, birthDate : this.birthdate, role : this.selectedRole};
      post = this.http.post("http://localhost:8080/register", params);
    } else {
      const params = {email : this.email, name : this.name, password : this.password,
                      subscription : this.selectedSub};
      post = this.http.post("http://localhost:8080/company-register", params);
    }
    post.subscribe(response => {
      const message = deserialize(Message, response);
      if (message.message.startsWith("Reg")) {
        this.errormessage = '';
        this.router.navigate(['']);
      } else {
        this.errormessage = 'E-mail already in use';
      }
    });
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
    if (this.selectedRole == 'STUDENT' || this.selectedRole == 'TEACHER') {
      if (this.email == null || this.password == null
          || this.firstname == null || this.lastname == null || this.birthdate == null) {
        return true;
      }
    } else {
      if (this.email == null || this.name == null || this.password == null) {
        return true;
      }
    }

    if (this.password != this.confpassword) {
      return true;
    }
    return false;
  }
}
