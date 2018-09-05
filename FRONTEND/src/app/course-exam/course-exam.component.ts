import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { User } from '../user';
import { Observable } from 'rxjs';
import { Exanswer } from '../exanswer';

@Component({
  selector: 'app-course-exam',
  templateUrl: './course-exam.component.html',
  styleUrls: ['./course-exam.component.scss']
})
export class CourseExamComponent implements OnInit {

  course: Course;
  contentLoaded : boolean = false;
  user : User;

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, private authService: AuthService, private dataService: DataService) { }

  ngOnInit() {
    this.route.url.subscribe(uri => {
      this.http.get<Course>('/api/courses/' + uri[1].path).subscribe(resp => {
        this.course = resp;
        this.authService.getAuth().subscribe(user => {
          this.user = user;
          this.contentLoaded = true;
        });
      });
    });
  }


  submitButtonClicked() {
    this.sendAnswers().subscribe(resp => {
      this.router.navigate(['exam-results/' + this.course.id]);
    });
  }

  addClass(event) {
    if (event.target.classList.contains('checked')) {
      event.target.classList.remove('checked');
      return;
    } else {
      event.target.classList.add('checked');
    }
  }

  sendAnswers() : Observable<void> {
    // student, course, question, answer (IDS!)
    let inputs = document.getElementsByClassName('checked');
    let x;
    for (let i = 0; i < inputs.length; i++) { 
      const body = { 'studentId' : this.user.user.id, 'courseId' : this.course.id, 'questionId' : inputs[i].getAttribute('name'), 'answerId' : inputs[i].id };
      x = this.http.post<void>('api/student-answers/answers', body);
      if (i != inputs.length - 1) {
        x.subscribe(resp => {});
      }
    }
    return x;
  }

  showCourse() {
    console.log(this.course);
  }
}
