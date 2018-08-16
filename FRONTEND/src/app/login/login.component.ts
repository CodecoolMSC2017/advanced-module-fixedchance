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
  }

  roleChosen(event) {
    if (this.prevSelectedRole != null) {
      this.prevSelectedRole.classList.remove('activerole');
    }
    this.prevSelectedRole = event.target;
    event.target.classList.add('activerole');
    this.selectedRole = event.target.name;
  }

  goHome() {
    this.router.navigate(['home']);
  }

  getAuth() {
    this.authService.setCurrentRole(this.selectedRole);
    // temp solution
    if (this.selectedRole === undefined) {
      this.authService.onLogOutClick();
    //  alert('Refresh the page and try again');
      this.router.navigate(['']);
    }
    if (this.selectedRole === 'STUDENT' || this.selectedRole === 'TEACHER') {
      if (this.loginDetails.password !== null) {
        this.authService.getAuth(this.loginDetails).subscribe(user => {
          this.goHome();
        }, error => alert(error.message));
      } else {
        this.authService.getAuth().subscribe(user => {
          this.goHome();
        }, error => alert(error.message));
      }
    } else {
      if (this.loginDetails.password !== null) {
        this.authService.getAuthCompany(this.loginDetails).subscribe(company => {
          this.company = company;
          this.goHome();
        }, error => alert(error.message));
      } else {
        this.authService.getAuth().subscribe(company => {
          // Should redirect to company's home page
          this.goHome();
        }, error => alert(error.message));
      }
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
    const role = this.selectedRole;
    const idToken = googleUser.getAuthResponse().id_token;
    const params = [idToken, role];
    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'api/google-login');
    xhr.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
    xhr.send(JSON.stringify(params));
    this.loginDetails.password = null;
    this.getAuth();
  }

  showGoogleLoginButton() {
    const hiddenButton = document.getElementById('google-login');
    hiddenButton.hidden = false;
  }

}
