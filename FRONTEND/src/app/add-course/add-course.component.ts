import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { AuthService } from '../auth.service';
import { User } from '../user';
import { AddCourse } from '../add-course';
import { AddVideo } from '../add-video';
import { AddQuestion } from '../add-question';
import { AddAnswer } from '../add-answer';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.scss']
})
export class AddCourseComponent implements OnInit {

  user: User;
  topics: String[] = [];
  currentTopic: string;
  course: AddCourse = new AddCourse();
  isDemo: boolean;
  coursePrice: number = 0;
  userLevel: number = 0;

  constructor(private authService: AuthService, private http: HttpClient, private route: ActivatedRoute, private router: Router, private dataService: DataService) { }

  ngOnInit() {
    this.http.get<User>('api/login/6').subscribe(resp => {
      this.user = resp;
      let experience = this.user.experience;
      while (experience - 1200 - this.userLevel * 300 >= 0) {
        experience -= 1200 + this.userLevel * 300;
        this.userLevel++;
      }
    });
  }

  ngDoCheck() {
    this.coursePrice = 5 + (0.6 * this.course.questions.length) + (0.5 * this.course.videos.length) + (0.3 * this.userLevel);
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

  topicKey(event) {
    if (event.key === ' ') {
      if (this.currentTopic !== ' ' && this.currentTopic != null) {
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

  incrementQuestionNum() {
    this.course.questions.push(new AddQuestion());
  }

  incrementAnswerNum(i) {
    this.course.questions[i].questionAnswers.push(new AddAnswer());
  }

  removeVideo(i) {
    this.course.videos.splice(i, 1);
    while (this.course.videos.length * 3 < this.course.questions.length) {
      this.course.questions.splice(-1, 1);
    }
  }

  removeQuestion(i) {
    this.course.questions.splice(i, 1);
  }

  removeAnswer(i, j) {
    this.course.questions[i].questionAnswers.splice(j, 1);
  }

  displayCourse() {
    console.log(this.course);
  }

  engage() {
    if (!this.isDemo) {
      let element: HTMLElement = document.getElementById('transition');
      element.classList.remove('hidden');
      element.classList.add('show');
      setTimeout(() => { this.demoMode() }, 1500);
      setTimeout(() => { element.classList.remove('show'); element.classList.add('hidden') }, 3000);
      let wrapper = document.createElement('div');
      wrapper.id = "embed";
      let embed = document.createElement('audio');
      embed.setAttribute('autoplay', 'true');
      embed.src = 'assets/demo-mode.mp3';
      wrapper.appendChild(embed);
      element.appendChild(wrapper);
      setTimeout(() => { wrapper.remove() }, 2800);
    } else {
      this.demoMode();
    }
  }

  demoMode() {
    this.course = new AddCourse();

    if (!this.isDemo) {
      this.isDemo = true;
      this.course.courseName = 'PostgreSQL Best Practices';
      this.course.topics = ['Postgres', 'PostgreSQL', 'SQL', 'Tips', 'Tricks', 'Best', 'Practices'];

      // videos
      this.course.videos.push(new AddVideo());
      this.course.videos[0].name = 'Creating a database schema';
      this.course.videos[0].description = 'Learn how to create a database schema';
      this.course.videos[0].url = 'https://www.youtube.com/watch?v=I_rxqSJAj6U';

      this.course.videos[1].name = 'Inserts';
      this.course.videos[1].description = 'My advices on writing insert statements';
      this.course.videos[1].url = 'https://www.youtube.com/watch?v=Ytb3BREgWGE';

      this.course.videos[2].name = 'Joins';
      this.course.videos[2].description = 'How joins work';
      this.course.videos[2].url = 'https://www.youtube.com/watch?v=-20RIzxdKZQ';

      this.course.videos[3].name = 'Updates';
      this.course.videos[3].description = 'What to keep in mind when updating records';
      this.course.videos[3].url = 'https://www.youtube.com/watch?v=wOY-KGOAfME';
      // videos

      // questions
      for (let i = 0; i < 5; i++) {
        this.course.questions.push(new AddQuestion());
      }
      this.course.questions[0].question = 'Select the best sequence for creating an INIT';
      this.course.questions[0].questionType = 'SA';
      this.course.questions[1].question = 'Select the correct syntax';
      this.course.questions[1].questionType = 'SA';
      this.course.questions[2].question = 'Select available join types';
      this.course.questions[2].questionType = 'MA';
      this.course.questions[3].question = 'Select the true statement(s)';
      this.course.questions[3].questionType = 'MA';
      this.course.questions[4].question = 'Can you declare enum types in SQL?';
      this.course.questions[4].questionType = 'YN';
      this.course.questions[5].question = 'You can override a table with a new one without dropping it first';
      this.course.questions[5].questionType = 'TF';
      this.course.questions[6].question = 'Explain aggregate functions';
      this.course.questions[6].questionType = 'WA';
      this.course.questions[7].question = "Explain the 'HAVING' clause";
      this.course.questions[7].questionType = 'WA';

      // first question
      for (let i = 0; i < 3; i++) {
        this.course.questions[0].questionAnswers.push(new AddAnswer());
      }
      this.course.questions[0].questionAnswers[0].answer = 'CREATE TABLES -> DELETE RECORDS -> DROP TABLES -> INSERTS';
      this.course.questions[0].questionAnswers[0].isRight = false;
      this.course.questions[0].questionAnswers[1].answer = 'DROP TABLES -> CREATE TABLES -> INSERTS';
      this.course.questions[0].questionAnswers[1].isRight = true;
      this.course.questions[0].questionAnswers[2].answer = 'INSERTS -> CREATE TABLES -> DROP TABLES';
      this.course.questions[0].questionAnswers[2].isRight = false;
      // first question

      // second question
      for (let i = 0; i < 4; i++) {
        this.course.questions[1].questionAnswers.push(new AddAnswer());
      }
      this.course.questions[1].questionAnswers[0].answer = 'INSERT INTO table_name -> value1, value2';
      this.course.questions[1].questionAnswers[0].isRight = false;
      this.course.questions[1].questionAnswers[1].answer = 'INSERT INTO table_name PUT value1, value2';
      this.course.questions[1].questionAnswers[1].isRight = false;
      this.course.questions[1].questionAnswers[2].answer = 'INSERT INTO table_name (key1, key2) VALUES (value1, value2)';
      this.course.questions[1].questionAnswers[2].isRight = true;
      this.course.questions[1].questionAnswers[3].answer = 'INSERT INTO table_name key1, key2 SET (value1, value2)';
      this.course.questions[1].questionAnswers[3].isRight = false;
      // second question

      // third question
      for (let i = 0; i < 5; i++) {
        this.course.questions[2].questionAnswers.push(new AddAnswer());
      }
      this.course.questions[2].questionAnswers[0].answer = 'INNER JOIN';
      this.course.questions[2].questionAnswers[0].isRight = true;
      this.course.questions[2].questionAnswers[1].answer = 'OUTER JOIN';
      this.course.questions[2].questionAnswers[1].isRight = true;
      this.course.questions[2].questionAnswers[2].answer = 'BASIC JOIN';
      this.course.questions[2].questionAnswers[2].isRight = false;
      this.course.questions[2].questionAnswers[3].answer = 'NO JOIN';
      this.course.questions[2].questionAnswers[3].isRight = false;
      this.course.questions[2].questionAnswers[4].answer = 'ALL JOIN';
      this.course.questions[2].questionAnswers[4].isRight = false;
      // third question

      // fourth question
      for (let i = 0; i < 3; i++) {
        this.course.questions[3].questionAnswers.push(new AddAnswer());
      }
      this.course.questions[3].questionAnswers[0].answer = 'You can group by without having aggregate function(s)';
      this.course.questions[3].questionAnswers[0].isRight = false;
      this.course.questions[3].questionAnswers[1].answer = 'You can have tables with the same name, if their fields are different';
      this.course.questions[3].questionAnswers[1].isRight = false;
      this.course.questions[3].questionAnswers[2].answer = 'You can insert more than one record within one statement';
      this.course.questions[3].questionAnswers[2].isRight = true;
      // fourth question

      // fifth question
      for (let i = 0; i < 2; i++) {
        this.course.questions[4].questionAnswers.push(new AddAnswer());
      }
      this.course.questions[4].questionAnswers[0].answer = 'Yes';
      this.course.questions[4].questionAnswers[0].isRight = true;
      this.course.questions[4].questionAnswers[1].answer = 'No';
      this.course.questions[4].questionAnswers[1].isRight = false;
      // fifth question

      // sixth question
      for (let i = 0; i < 2; i++) {
        this.course.questions[5].questionAnswers.push(new AddAnswer());
      }
      this.course.questions[5].questionAnswers[0].answer = 'True';
      this.course.questions[5].questionAnswers[0].isRight = false;
      this.course.questions[5].questionAnswers[1].answer = 'False';
      this.course.questions[5].questionAnswers[1].isRight = true;
      // sixth queston

      // seventh question
      this.course.questions[6].questionAnswers.push(new AddAnswer());
      this.course.questions[6].questionAnswers[0].answer = 'An aggregate function performs a calculation on a set of values, and returns a single value.';
      this.course.questions[6].questionAnswers[0].isRight = true;
      // seventh question

      // eighth question
      this.course.questions[7].questionAnswers.push(new AddAnswer());
      this.course.questions[7].questionAnswers[0].answer = 'A HAVING clause in SQL specifies that an SQL SELECT statement should only return rows where aggregate values meet the specified conditions.';
      this.course.questions[7].questionAnswers[0].isRight = true;
      // eighth question
      // questions
    } else {
      this.isDemo = false;
      this.course = new AddCourse();
    }
  }

  saveCourse() {
    this.http.post('/api/courses/' + this.user.user.id, { 'name': this.course.courseName, 'isValidated': true }).subscribe(courseId => {
      this.saveQuestions(courseId).subscribe(questionResp => {
        this.saveTopics(courseId).subscribe(topicResp => {
          this.saveVideos(courseId).subscribe(videoResp => {
            this.http.get('/api/courses/' + courseId).subscribe(course => {
              this.router.navigate(['courses/' + courseId]);
            });
          });
        });
      });
    });
  }

  saveQuestions(courseId): Observable<any> {
    let x;
    for (let i = 0; i < this.course.questions.length; i++) {
      x = this.http.post('/api/courses/' + courseId + '/questions', {
        'question': this.course.questions[i].question, 'questionType': this.course.questions[i].questionType });
      x.subscribe(questionId => { this.saveAnswers(i, courseId, questionId);
      });
    }
    return x;
  }

  saveAnswers(i, courseId, questionId) {
    for (let j = 0; j < this.course.questions[i].questionAnswers.length; j++) {
      this.http.post('/api/courses/' + courseId + '/questions/' + questionId + '/answers', {
        'answer': this.course.questions[i].questionAnswers[j].answer, 'isRight': this.course.questions[i].questionAnswers[j].isRight
      }).subscribe(answerId => {
       });
    }
  }

  saveTopics(courseId): Observable<any> {
    let x;
    for (let i = 0; i < this.course.topics.length; i++) {
      x = this.http.post('/api/courses/' + courseId + '/topics', {
        'name': this.course.topics[i] });
      x.subscribe(topicId => { });
    }
    return x;
  }

  saveVideos(courseId): Observable<any> {
    let x;
    for (let i = 0; i < this.course.videos.length; i++) {
      console.log(this.course.videos[i]);
      x = this.http.post('/api/courses/' + courseId + '/videos', {
        'name': this.course.videos[i].name, 'video': this.course.videos[i].url, 'description': this.course.videos[i].description });
      x.subscribe(videoId => { });
    }
    return x;
  }

  isChecked(i, j, event) {
    if (this.course.questions[i].questionAnswers[j].isRight) {
      return 'checked';
    }
    console.log(event.target);
    event.target.removeAttr('checked');
  }

  modifyRadios(i, j) {
    for (let k = 0; k < this.course.questions[i].questionAnswers.length; k++) {
      if (j == this.course.questions[i].questionAnswers[k]) {
        this.course.questions[i].questionAnswers[k].isRight = true;
      } else {
        this.course.questions[i].questionAnswers[k].isRight = false;
      }
    }
  }
}


