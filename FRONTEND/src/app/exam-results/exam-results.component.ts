import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Course } from '../course';
import { AuthService } from '../auth.service';
import { User } from '../user';
import { Exanswer } from '../exanswer';
import { Question } from '../question';

@Component({
  selector: 'app-exam-results',
  templateUrl: './exam-results.component.html',
  styleUrls: ['./exam-results.component.scss']
})
export class ExamResultsComponent implements OnInit {

  user : User;
  course : Course;
  contentLoaded : boolean = false;
  exanswer : Exanswer[];


  constructor(private route : ActivatedRoute, private http : HttpClient, private authService : AuthService) { }

  ngOnInit() {
    this.route.url.subscribe(uri => {
      this.http.get<Course>('/api/courses/' + uri[1].path).subscribe(resp => {
        this.course = resp;
        this.authService.getAuth().subscribe(user => {
          this.user = user;
          this.http.get<Exanswer[]>('api/exanswers/' + this.course.id + '/' + this.user.user.id).subscribe(exanswer => {
            this.exanswer = exanswer;
            console.log(this.exanswer);
            for (let i = 0; i < exanswer.length; i++) {
              this.http.get<Question>('api/courses/questions/' + exanswer[i].question).subscribe(resp => {
                exanswer[i].question = resp;
              });
            }
            this.contentLoaded = true;
            console.log(this.exanswer);
          });
        });
      });
    });
  }
}
