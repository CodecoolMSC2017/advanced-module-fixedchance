import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';
import { deserialize } from 'json-typescript-mapper';
import { User } from '../user';

@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

  email : string = this.email;
  password : string = this.password;
  params : URLSearchParams = new URLSearchParams;
  result : string;
  selectedRole : string;
  prevSelectedRole : Element;

  user : any;

  constructor(private http : HttpClient, private route: ActivatedRoute, private router: Router, public dataService : DataService) { }

  ngOnInit() {
  }

  onLoginClick() {
    if (this.email == null) {
      return;
    }
    if (this.password == null) {
      return;
    }

    console.log(this.route.data);

    const params = {email : this.email, password : this.password};
    if (this.selectedRole == 'STUDENT' || this.selectedRole == 'TEACHER') {
      this.http.post("http://localhost:8080/login", params).subscribe(response => {
        if (response == null) {
          return;
        } else {
          this.user = deserialize(User, response);
          console.log(this.user);
          this.dataService.setUser(this.user);
          console.log(this.dataService.user);
          this.router.navigate(['home']);
        }
      }); 
    } else {
      this.http.post("http://localhost:8080/company-login", params).subscribe(response => {
        if (response == null) {
          return;
        } else {
          this.router.navigate(['home']);
        }
      });
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
}
