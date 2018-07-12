import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss',
              './main.component.anim.scss']
})
export class MainComponent implements OnInit {

  isSignIn : boolean = false;
  isSignUp : boolean = false;
  selectedRole : string;
  prevSelectedRole : Element;

  isStudentOverView : boolean;
  isTeacherOverView : boolean;
  isCompanyOverView : boolean;

  

  constructor() { }

  ngOnInit() {
  }

  onSignInClick() {
    this.isSignIn = true;
  }

  onSignUpClick() {
    this.isSignUp = true;
  }

  backToMain() {
    this.isSignIn = false;
    this.isSignUp = false;
  }

  choosenTile(event) {
    if (this.prevSelectedRole != null) {
      this.prevSelectedRole.classList.remove('activerole');
    }
    this.prevSelectedRole = event.target;
    event.target.classList.add('activerole');
    this.selectedRole = event.target.name;
    console.log(this.selectedRole);
  }

  onEntryHover(event) {
    const studentElement : HTMLElement = document.getElementById('student-overview');
    const teacherElement : HTMLElement = document.getElementById('teacher-overview');
    const companyElement : HTMLElement = document.getElementById('company-overview');
    event.target.classList.add('overhover');
    
    switch (event.target.id) {
      case 'student-overview':
        this.isStudentOverView = true;
        this.isTeacherOverView = false;
        this.isCompanyOverView = false;
        teacherElement.classList.add('nonhover');
        companyElement.classList.add('nonhover');
        break;
      case 'teacher-overview':
        this.isStudentOverView = false;
        this.isTeacherOverView = true;
        this.isCompanyOverView = false;
        studentElement.classList.add('nonhover');
        companyElement.classList.add('nonhover');
        break;
      case 'company-overview':
        this.isStudentOverView = false;
        this.isTeacherOverView = false;
        this.isCompanyOverView = true;
        studentElement.classList.add('nonhover');
        teacherElement.classList.add('nonhover');
        break;
    }
  }

  onEntryLeave(event) {
    const studentElement : HTMLElement = document.getElementById('student-overview');
    const teacherElement : HTMLElement = document.getElementById('teacher-overview');
    const companyElement : HTMLElement = document.getElementById('company-overview');
    event.target.classList.remove('overhover');

    switch (event.target.id) {
      case 'student-overview':
        teacherElement.classList.remove('nonhover');
        companyElement.classList.remove('nonhover');
        break;
      case 'teacher-overview':
        studentElement.classList.remove('nonhover');
        companyElement.classList.remove('nonhover');
        break;
      case 'company-overview':
        studentElement.classList.remove('nonhover');
        teacherElement.classList.remove('nonhover');
        break;
    }
    this.isStudentOverView = false;
    this.isTeacherOverView = false;
    this.isCompanyOverView = false;


  }
}
