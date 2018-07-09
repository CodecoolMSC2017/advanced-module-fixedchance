import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from '../message';
import { deserialize } from 'json-typescript-mapper';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  email : string = this.email;
  password : string = this.password;
  confpassword : string = this.confpassword;
  message : Message;
  errormessage: string;


  constructor(private http : HttpClient, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
  }

  onRegisterClick() {
    if (this.email == null || this.password == null) {
      return;
    }
    if (this.password != this.confpassword) {
      return;
    }

    const params = {email : this.email, password : this.password, confpassword : this.confpassword};
    this.http.post("http://localhost:8080/register", params).subscribe(response => {
      const message = deserialize(Message, response);
      if (message.message.startsWith("Reg")) {
        this.errormessage = '';
        this.router.navigate(['']);
      } else {
        this.errormessage = 'E-mail already in use';
      }
    });
  }
}
