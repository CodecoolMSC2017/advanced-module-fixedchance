import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';

@Component({
  selector: 'app-course-checkout',
  templateUrl: './course-checkout.component.html',
  styleUrls: ['./course-checkout.component.css']
})
export class CourseCheckoutComponent implements OnInit {

  price : number;
  coursePrice : number;
  errormessage : string;

  constructor(private dataService : DataService, private http : HttpClient, private route : ActivatedRoute, private router : Router) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.coursePrice = params.price;
    });
  }

  onSubmit() {
    if (this.price >= this.coursePrice) {
      this.errormessage = 'Successful. Redirecting you to the course-page';
      this.http.post("api/course-student", { 'courseId' : this.dataService.getCurrentCourse().id, 'studentId' : this.dataService.getUser().id });
      setTimeout(() => {
        this.router.navigate(['course'], { queryParams : { available : false } })}, 2000);
    } else {
      this.errormessage = 'Confirmation price must be equal or higher than minimum price';
    }
  }

  onBack() {
    this.router.navigate(['course'], { queryParams : { available : true } });
  }
}
