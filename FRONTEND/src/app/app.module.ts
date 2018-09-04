import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { DataService } from './data.service';
import { UploadFileService } from './upload-file.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { HomeComponent } from './home/home.component';
import { MainComponent } from './main/main.component';
import { ProfileComponent } from './profile/profile.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { AddCourseComponent } from './add-course/add-course.component';
import { DatePipe } from '@angular/common';
import { httpInterceptorProviders } from './http-interceptors';
import { CoursesComponent } from './courses/courses.component';
import { CourseComponent } from './course/course.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgCircleProgressModule } from 'ng-circle-progress';
import { SafePipe } from './safepipe.pipe';
import { CourseExamComponent } from './course-exam/course-exam.component';
import { UsersComponent } from './users/users.component';
import { GuestProfileComponent } from './guest-profile/guest-profile.component';
import { OrderModule } from 'ngx-order-pipe';
import { HomeCompanyComponent } from './home-company/home-company.component';
import { CommentComponent } from './comment/comment.component';
import { UserService } from './user.service';
import { User } from './user';
import { SuccessfulPaymentComponent } from './successful-payment/successful-payment.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    MainComponent,
    ProfileComponent,
    NotfoundComponent,
    AddCourseComponent,
    CoursesComponent,
    CourseComponent,
    SafePipe,
    CourseExamComponent,
    UsersComponent,
    GuestProfileComponent,
    HomeCompanyComponent,
    CommentComponent,
    SuccessfulPaymentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    NgbModule.forRoot(),
    NgCircleProgressModule.forRoot({
    'space': -10,
    'outerStrokeWidth': 10,
    'outerStrokeColor': 'orange',
    'innerStrokeColor': 'white',
    'innerStrokeWidth': 10,
    'animateTitle': false,
    'animationDuration': 100,
    'showUnits': false,
    'showBackground': false,
    'clockwise': true,
    'responsive': false,
    'startFromZero': false,
    'titleColor': 'white',
    'renderOnClick': false,
    'maxPercent': 100}),
    OrderModule,
    HttpModule,
  ],
  providers: [DataService, UserService, User, DatePipe, UploadFileService, httpInterceptorProviders, HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
