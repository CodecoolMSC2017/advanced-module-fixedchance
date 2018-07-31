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
import { CourseCheckoutComponent } from './course-checkout/course-checkout.component';

const routes: Routes = [
  {
    path: '',
    component: MainComponent
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
    path: 'profile',
    component: ProfileComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'add-course',
    component: AddCourseComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'courses',
    component: CoursesComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'course',
    component: CourseComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'course-exam',
    component: CourseExamComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'course-checkout',
    component: CourseCheckoutComponent,
    canActivate: [LoginGuard],
  },
  {
    path: '**',
    component: NotfoundComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

