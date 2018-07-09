import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
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
}
