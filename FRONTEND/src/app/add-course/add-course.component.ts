import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';
import { User } from '../user';
import { Video } from '../video';
import { AddCourse } from '../add-course';
import { AddVideo } from '../add-video';
import { AddQuestion } from '../add-question';
import { AddAnswer } from '../add-answer';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.scss']
})
export class AddCourseComponent implements OnInit {

  user : User;
  topics : String[] = [];
  currentTopic : string;
  course : AddCourse = new AddCourse();

  constructor(private authService : AuthService, private http: HttpClient, private route: ActivatedRoute, private router: Router, private dataService: DataService) { }

  ngOnInit() {
    this.http.get<User>("api/login/5").subscribe(resp => { this.user = resp });
  }
  
  onChange(i) {
    if (this.course.questions[i].questionAnswers.length > 0) {
      this.course.questions[i].questionAnswers = [];
    } 
    if (this.course.questions[i].questionType === 'SA' || this.course.questions[i].questionType === 'MA') {
      for (let k = 0; k < 3; k++) {
        this.course.questions[i].questionAnswers.push(new AddAnswer());
      }
    } else if (this.course.questions[i].questionType === 'YN' || this.course.questions[i].questionType === 'TF') {
      for (let k = 0; k < 2; k++) {
        this.course.questions[i].questionAnswers.push(new AddAnswer());
      }
    } else {
      this.course.questions[i].questionAnswers.push(new AddAnswer());
    }
  }

  onAddNewCourseClick() {
    
  }

  onLogoutClick() {
    this.authService.deleteAuth();
  }

  topicKey(event) {
    if (event.key === ' ') {
      if (this.currentTopic != " " && this.currentTopic != null) {
        this.currentTopic = this.currentTopic.trim();
          if (!this.course.topics.some(x => x === this.currentTopic) && this.course.topics.length <= 10) {
            this.course.topics.push(this.currentTopic);
          }
      }
      this.currentTopic = '';
    }
  }

  removeTopic(event) {
    let topicToRemove = event.target.id;
    for (let i = 0; i < this.course.topics.length; i++) {
      if (this.course.topics[i] === topicToRemove) {
        this.course.topics.splice(i, 1);
      }
    }
  }

  incrementVideoNum() {
    this.course.videos.push(new AddVideo());
    if (this.course.questions.length <= this.course.videos.length * 3) {
      this.course.questions.push(new AddQuestion());
    }
  }

  removeVideo(event) {
    let videoToRemove = event.target.getAttribute("vidnum");
    this.course.videos.splice(videoToRemove, 1);
    while (this.course.videos.length * 3 < this.course.questions.length) {
      this.course.questions.splice(-1, 1);
    }
  }

  displayCourse() {
    console.log(this.course);
  }
}


