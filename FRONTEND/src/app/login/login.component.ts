import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';
import { LoginDetails } from '../login-details';
import { deserialize } from 'json-typescript-mapper';
import { User } from '../user';

@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

  loginDetails: LoginDetails = new LoginDetails();
  email : string = this.email;
  password : string = this.password;
  params : URLSearchParams = new URLSearchParams;
  result : string;
  selectedRole : string;
  prevSelectedRole : Element;
  username : string = this.username;

  user : any;

  constructor(private authService: AuthService, private http : HttpClient, private route: ActivatedRoute, private router: Router, public dataService : DataService) { }

  ngOnInit() {
  }

  onLoginClick() {
    if (this.loginDetails.username == null) {
      return;
    }
    if (this.loginDetails.password == null) {
      return;
    }
    this.getAuth();
    this.user = JSON.parse(sessionStorage.getItem('user'));
    if (this.selectedRole == 'STUDENT' || this.selectedRole == 'TEACHER') {
        if (this.user == null) {
          return;
        } else {
          this.dataService.setUser(this.user);
          console.log(this.user);
          this.router.navigate(['home']);
        } 
    } else {
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

  getAuth() {
    return this.authService.getAuth(this.loginDetails).subscribe(user => {
      sessionStorage.setItem('user', JSON.stringify(user));
    }, error => alert(error.message));
  }
}
