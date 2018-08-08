import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TeacherContentGuard implements CanActivate {

  constructor(private route : ActivatedRoute, private router : Router, private authService : AuthService) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    console.log(this.authService.getCurrentRole());
    if (this.authService.getCurrentRole() == 'TEACHER') {
        return true;
      } else {
        this.router.navigate(['']);
        return false;
      }
  }
}
