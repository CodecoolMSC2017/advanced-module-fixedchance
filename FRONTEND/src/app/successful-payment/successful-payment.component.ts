import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-successful-payment',
  templateUrl: './successful-payment.component.html',
  styleUrls: ['./successful-payment.component.css']
})
export class SuccessfulPaymentComponent implements OnInit {

  constructor(private http : HttpClient, private router : Router) { }

  ngOnInit() {
    let courseId = localStorage.getItem("purchaseCourse");
    let userId = localStorage.getItem("purchaseStudent");
    this.http.post('api/course-student', { 'courseId':  courseId, 'studentId': userId })
    .subscribe(resp => { 
      this.router.navigate(['courses/' + courseId]);
    });
  }

}
