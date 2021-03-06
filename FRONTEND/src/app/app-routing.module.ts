import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LoginGuard } from './login.guard';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { MainComponent } from './main/main.component';
import { ProfileComponent } from './profile/profile.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { AddCourseComponent } from './add-course/add-course.component';
import { CoursesComponent } from './courses/courses.component';
import { CourseComponent } from './course/course.component';
import { CourseExamComponent } from './course-exam/course-exam.component';
import { UsersComponent } from './users/users.component';
import { GuestProfileComponent } from './guest-profile/guest-profile.component';
import { TeacherContentGuard } from './teacher-content.guard';
import { HomeCompanyComponent } from './home-company/home-company.component';
import { CommentComponent } from './comment/comment.component'
import { SuccessfulPaymentComponent } from './successful-payment/successful-payment.component';
import { ExamResultsComponent } from './exam-results/exam-results.component';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'company/home',
    component: HomeCompanyComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'add-course',
    component: AddCourseComponent,
    canActivate: [LoginGuard, TeacherContentGuard],
  },
  {
    path: 'courses',
    component: CoursesComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'courses/:id',
    component: CourseComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'course-exam/:id',
    component: CourseExamComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'users',
    component: UsersComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'users/:id',
    component: GuestProfileComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'comment/:id',
    component: CommentComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'successful',
    component: SuccessfulPaymentComponent
  },
  {
    path: 'exam-results/:courseid',
    component: ExamResultsComponent
  },
  {
    path: '**',
    component: NotfoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

