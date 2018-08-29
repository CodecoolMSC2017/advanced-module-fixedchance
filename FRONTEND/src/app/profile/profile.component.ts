import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { DataService } from '../data.service';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { AuthService } from '../auth.service';
import { Course } from '../course';
import { HttpResponse } from '@angular/common/http';
import { UploadFileService } from '../upload-file.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  userLevel = 0;
  currentExp: number;
  expToNextLevel: number;
  user: User;
  percentage: number;
  // stores the date in the right format (yyyy-MM-dd)
  userBirthDate: string;
  xpNum: number;
  showCourse: boolean;
  courses: Course[];
  contentLoaded: boolean;

  selectedFiles: File[] = [];
  currentFileUpload: File;

  courseEntries: Array<String>;

  blob: any;

  constructor(private uploadService: UploadFileService,
    private authService: AuthService,
    private dataService: DataService,
    private router: Router,
    private datePipe: DatePipe,
    private userService: UserService) { }

  ngOnInit() {
    this.authService.getAuth().subscribe(resp => {
      this.user = resp;
      if (this.user.user.authorities[0] === 'ROLE_TEACHER') {
        this.dataService.fetchTeacherCourses(this.user.id).subscribe(response => {
          this.courses = response;
        });
      } else if (this.user.user.authorities[0] === 'ROLE_STUDENT') {
        this.dataService.fetchStudentCourses(this.user.id).subscribe(response => {
          this.courses = response;
        });
      }
      // convert date to the right format
      this.userBirthDate = this.datePipe.transform(this.user.birthDate, 'yyyy-MM-dd');

      // xp calculations
      this.calculateXp();
      this.contentLoaded = true;
    });
  }

  onTeacherCourseClick(event) {
    this.router.navigate(['course-edit/' + event.target.id]);
  }

  onStudentCourseClick(event) {
    this.router.navigate(['courses/' + event.target.id]);
  }

  findCourseById(id): Course {
    for (let i = 0; i < this.courses.length; i++) {
      if (this.courses[i].id === id) {
        return this.courses[i];
      }
    }
  }

  // the user can add courses if role is teacher or admin
  isValid() {
    if (this.user.role === 'TEACHER' || this.user.role === 'ADMIN') {
      return true;
    } else {
      return false;
    }
  }

  addXp() {
    this.user.experience += 100;
    this.calculateXp();
  }

  calculateXp() {
    this.userLevel = 0;
    this.currentExp = 0;
    this.expToNextLevel = 1200;
    this.percentage = 0;
    let experience = this.user.experience;

    while (experience - 1200 - this.userLevel * 300 >= 0) {
      experience -= 1200 + this.userLevel * 300;
      this.userLevel++;
    }
    this.currentExp = experience;
    this.expToNextLevel = 1200 + this.userLevel * 300;
    this.percentage = Math.round((this.currentExp / this.expToNextLevel) * 100);
  }

  showCourses(event) {
    this.showCourse = true;
    switch (event.target.id) {
      case 'programming':
        this.courseEntries = ['Python for dummies', 'Python from zero to hero', 'Python advanced', 'Python master',
        'Python for web developers', 'Java for dummies', 'Java from zero to hero', 'Java advanced',
        'Java master', 'Java for web developers'];
        break;
      case 'videos':
        this.courseEntries = ['Fiery effect tutorial', 'Manipulate sound effects', 'Special effects', 'CGI tutorial'];
        break;
      case 'photos':
        this.courseEntries = ['All about exposure', 'How to change hair color', '3D effect for text tutorial', 'Masking for dummies'];
        break;
      case 'softs':
        this.courseEntries = ['A way to successful job interviews', 'HR questions and answers', 'You can be charismatic as well!'];
        break;
    }
  }

  backToProfile() {
    this.showCourse = false;
  }

  getExp(): string {
    return this.currentExp + ' / ' + this.expToNextLevel;

  }

  selectFile(event) {
    const file = event.target.files.item(0);
    if (file.type.match('image.*')) {
      this.selectedFiles = event.target.files;
      this.upload();
    } else {
      alert('invalid format!');
    }
  }

  upload() {
    this.currentFileUpload = this.selectedFiles[0];
    this.uploadService.pushFileToStorage(this.currentFileUpload, this.user.user.username).subscribe(event => {
      if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
        this.uploadService.getFile(this.user.user.username).subscribe(resp => { 
          this.blob = window.URL.createObjectURL(resp);
        });
      } else {
        console.log('asd');
      }
    });
    this.selectedFiles = undefined;
  }
}
