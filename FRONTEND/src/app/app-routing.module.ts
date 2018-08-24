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
import { UsersComponent } from './users/users.component';
import { GuestProfileComponent } from './guest-profile/guest-profile.component';
import { TeacherContentGuard } from './teacher-content.guard';
import { HomeCompanyComponent } from './home-company/home-company.component';

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
    path: 'home-company',
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
    path: 'course-checkout/:id',
    component: CourseCheckoutComponent,
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
    path: '**',
    component: NotfoundComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

