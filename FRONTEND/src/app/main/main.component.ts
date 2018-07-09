import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  isSignIn : boolean = false;
  isSignUp : boolean = false;

  constructor() { }

  ngOnInit() {
  }

  onSignInClick() {
    this.isSignIn = true;
  }

  onSignUpClick() {
    this.isSignUp = true;
  }

  backToMain() {
    this.isSignIn = false;
    this.isSignUp = false;
  }

}
