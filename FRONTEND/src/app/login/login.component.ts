import { Component, OnInit, ViewEncapsulation, AfterViewInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';
import { LoginDetails } from '../login-details';

// Google's login API namespace
declare const gapi: any;

@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})

export class LoginComponent implements OnInit, AfterViewInit {

  loginDetails: LoginDetails = new LoginDetails();
  selectedRole: string;
  prevSelectedRole: Element;
  user: any;
  company: any;
  // google login
  googleLoginButton = 'google-login-button';

  constructor(private authService: AuthService, private http: HttpClient, private route: ActivatedRoute, private router: Router,
    public dataService: DataService) { }

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
    /*
    this.user = JSON.parse(sessionStorage.getItem('user'));
    if (this.selectedRole === 'STUDENT' || this.selectedRole === 'TEACHER') {
      if (this.user == null) {
        return;
      } else {
        this.dataService.setUser(this.user);
        this.router.navigate(['home']);
      }
    } else {
    }
    */
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
      this.authService.setCurrentRole(this.selectedRole);
      if (this.selectedRole === 'STUDENT' || this.selectedRole === 'TEACHER') {
    this.authService.getAuth(this.loginDetails).subscribe(user => {
      this.router.navigate(['home']);
    }, error => alert(error.message));
  } else {
    this.authService.getAuthCompany(this.loginDetails).subscribe(company => {
      this.company = company;
      this.router.navigate(['home']);
    }, error => alert(error.message));
  }
}


  // Angular hook that allows for interaction with elements inserted by the
  // rendering of a view.
  ngAfterViewInit() {
    // Converts the Google login button stub to an actual button.
    gapi.signin2.render(
      this.googleLoginButton,
      {
        'onSuccess': this.onGoogleLoginSuccess,
        'scope': 'profile',
        'theme': 'light',
        'width': '148',
        'height': '42'
      });
  }

  onGoogleLoginSuccess = (googleUser) => {
    const idToken = googleUser.getAuthResponse().id_token;
    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'api/google-login');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(idToken);
    // After get back the user -> auth -> redirect
    // this.router.navigate(['home']);
  }

  showGoogleLoginButton() {
    const hiddenButton = document.getElementById('google-login');
    hiddenButton.hidden = false;
  }

}
