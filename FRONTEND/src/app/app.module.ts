import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { DataService } from './data.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { MainComponent } from './main/main.component';
import { ProfileComponent } from './profile/profile.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { RoundProgressModule } from 'angular-svg-round-progressbar';
import { AddCourseComponent } from './add-course/add-course.component';
import { DatePipe } from '@angular/common';
<<<<<<< Updated upstream
import { httpInterceptorProviders } from './http-interceptors';
=======
import { CoursesComponent } from './courses/courses.component';
>>>>>>> Stashed changes

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
    CoursesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RoundProgressModule,
  ],
  providers: [DataService, DatePipe, httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
