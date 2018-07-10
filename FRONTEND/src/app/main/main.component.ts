import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  isSignIn : boolean = false;
  isSignUp : boolean = false;
  selectedRole : string;
  prevSelectedRole : Element;

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

  choosenTile(event) {
    if (this.prevSelectedRole != null) {
      this.prevSelectedRole.classList.remove('activerole');
    }
    this.prevSelectedRole = event.target;
    event.target.classList.add('activerole');
    this.selectedRole = event.target.name;
    console.log(this.selectedRole);
  }

}
