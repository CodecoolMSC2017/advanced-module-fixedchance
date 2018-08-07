import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-course-exam',
  templateUrl: './course-exam.component.html',
  styleUrls: ['./course-exam.component.scss']
})
export class CourseExamComponent implements OnInit {

  course : Course;

  constructor(private http : HttpClient, private route : ActivatedRoute, private router : Router, private authService : AuthService, private dataService : DataService) {}

  ngOnInit() {
    this.route.url.subscribe(uri => {
      this.http.get<Course>('/api/courses/'+uri[1].path).subscribe(resp => {
        this.course = resp;
      });
    });
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
