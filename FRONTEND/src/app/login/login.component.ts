import { Component, OnInit, ViewEncapsulation, AfterViewInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';
import { LoginDetails } from '../login-details';
import { Observable } from 'rxjs';
import { UserService } from '../user.service';

// Google's login API namespace
declare const gapi: any;

@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})

export class LoginComponent implements OnInit, AfterViewInit {

  message: string;
  loginDetails: LoginDetails = new LoginDetails();
  selectedRole: string;
  prevSelectedRole: Element;
  user: any;
  company: any;
  // google login
  googleLoginButton = 'google-login-button';

  constructor(private authService: AuthService, private http: HttpClient, private route: ActivatedRoute, private router: Router,
    public dataService: DataService, private userService: UserService) { }

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

  goHomeWithCompany() {
    this.router.navigate(['company/home']);
  }

  getAuth() {
    // if a previous login happened with google
    // must delete the profile picture from userService
    if (this.userService.userPicture !== 'undefined') {
      this.userService.userPicture = null;
    }
    this.authService.setCurrentRole(this.selectedRole);
    if (this.selectedRole === 'STUDENT' || this.selectedRole === 'TEACHER') {
      if (this.loginDetails.password !== null) {
        this.authService.getAuth(this.loginDetails).subscribe(user => {
          if (user !== null && user.user.authorities[0].split('_')[1] === this.selectedRole ||
          user !== null && user.user.authorities[0].split('_')[1] === 'ADMIN') {
          this.goHome();
          } else {
            this.message = 'Wrong role selected';
          }
        }, error => this.message = 'Wrong username or password');
      } else {
        this.authService.getAuth().subscribe(user => {
          this.goHome();
        }, error => alert(error.message));
      }
    } else if (this.selectedRole === 'COMPANY') {
      if (this.loginDetails.password !== null) {
        this.authService.getAuthCompany(this.loginDetails).subscribe(company => {
          if (company !== null && company.user.authorities[0].split('_')[1] === this.selectedRole) {
            this.goHomeWithCompany();
            } else {
              this.message = 'Wrong role selected';
            }
        }, error => this.message = 'Wrong username or password');
      } else {
        this.authService.getAuthCompany().subscribe(company => {
          this.goHomeWithCompany();
        }, error => alert(error.message));
      }
    } else {
      this.authService.onLogOutClick();
      this.router.navigate(['']);
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
    this.loginDetails.password = null;
    // This 'if' condition is just a quick and TEMPORARY solution
    // for solve the 'empty role' issue.
    // TODO: solve it
    if (role === undefined) {
      this.authService.onLogOutClick();
      this.router.navigate(['']);
      location.reload();
    } else {
    this.sendToken([idToken, role]).subscribe(user => {
      this.getAuth();

    // tmp pic containing solution - should store in the DB
    const userPicture = googleUser.w3.Paa;
    this.userService.setUserPicture(userPicture);

    }, error => this.message = error.error.message);
  }
}


  sendToken(params: string[]): Observable<any> {
    const url = '/api/google-login';
    const headers = new HttpHeaders({
      'Content-Type': 'application/json;charset=UTF-8'
    });
    const options = { headers: headers };
    return this.http.post(url, JSON.stringify(params), options);
  }

  showGoogleLoginButton() {
    const hiddenButton = document.getElementById('google-login');
    hiddenButton.hidden = false;
  }

}
