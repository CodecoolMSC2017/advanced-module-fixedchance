import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { ActivatedRoute } from '@angular/router';
import { UserEntry } from '../user-entry';
import { AuthService } from '../auth.service';
import { Course } from '../course';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-guest-profile',
  templateUrl: './guest-profile.component.html',
  styleUrls: ['./guest-profile.component.scss']
})
export class GuestProfileComponent implements OnInit {

  dispUser : UserEntry;
  courses : Course[];
  contentLoaded : boolean = false;

  constructor(private dataService : DataService, private route : ActivatedRoute, private authService : AuthService, private datePipe : DatePipe) { }

  ngOnInit() {
    this.route.url.subscribe(username => {
      this.dataService.getGuestUser(username[1]).subscribe(user => {
        user.level = this.dataService.calculateLevel(user.experience);
        user.regDate = this.datePipe.transform(user.registrationDate,"yyyy-MM-dd");
        user.role = user.role.substring(5, user.role.length);
        this.dispUser = user;
        if (this.dispUser.role === 'STUDENT') {
          this.dataService.fetchStudentCourses(this.dispUser.id).subscribe(courses => {
            this.courses = courses;
            this.contentLoaded = true;
          });
        } else {
          this.dataService.fetchTeacherCourses(this.dispUser.id).subscribe(courses => {
            this.courses = courses;
            this.contentLoaded = true;
          });
        } 
      });
    });
  }

  onLogoutClick() {
    this.authService.deleteAuth();
  }
}
