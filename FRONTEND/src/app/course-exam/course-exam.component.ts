import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-exam',
  templateUrl: './course-exam.component.html',
  styleUrls: ['./course-exam.component.scss']
})
export class CourseExamComponent implements OnInit {

  course : Course;

  constructor(private router : Router, private authService : AuthService, private dataService : DataService) { 
    this.course = this.dataService.getCurrentCourse();
  }

  ngOnInit() {
  }

  onLogoutClick() {
    this.authService.deleteAuth();
  }

  submitButtonClicked() {
    let inputs = document.getElementsByClassName('checked');
    for (let i = 0; i < inputs.length; i++) {
      console.log(inputs[i]);
    }
    this.router.navigate(['exam-results']);
  }

  addClass(event) {
    if (event.target.classList.contains('checked')) {
      event.target.classList.remove('checked');
      return;
    } else {
      event.target.classList.add('checked');
    }
  }
}
