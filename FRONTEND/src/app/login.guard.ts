import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { Observable, of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    if (this.authService.getCurrentRole() === 'STUDENT' || this.authService.getCurrentRole() === 'TEACHER') {
      return this.authService.getAuth().pipe(
        map(user => true),
        catchError(error => {
          this.router.navigate(['']);
          return of(false);
        }
        ));
    } else {
      return this.authService.getAuthCompany().pipe(
        map(company => true),
        catchError(error => {
          this.router.navigate(['']);
          return of(false);
        }
        ));
    }
  }
}
