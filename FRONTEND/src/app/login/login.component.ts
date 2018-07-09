import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  email : string = this.email;
  password : string = this.password;
  params : URLSearchParams = new URLSearchParams;
  result : string;
  user: Object;
  selectedRole : string;
  prevSelectedRole : Element;

  constructor(private http : HttpClient, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
  }

  onLoginClick() {
    if (this.email == null) {
      return;
    }
    if (this.password == null) {
      return;
    }

    const params = {email : this.email, password : this.password};
    this.http.post("http://localhost:8080/login", params).subscribe(response => { 
      if (response == null) {
        return;
      } else {
        this.router.navigate(['home']);
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
    console.log(this.selectedRole);
  }
}
