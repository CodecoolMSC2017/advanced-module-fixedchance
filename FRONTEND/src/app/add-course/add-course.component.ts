import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from 'src/app/message';
import { DataService } from '../data.service';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.scss']
})
export class AddCourseComponent implements OnInit {

  name: String = this.name;
  userId: number = this.dataService.user.id;

  links = ["link"];
  linkNumber = 1;

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, private dataService: DataService) { }

  ngOnInit() {
  }

  onAddNewCourseClick() {
    const params = { teacherId: this.userId, name: this.name };
    let post = this.http.post("http://localhost:8080/courses/add", params);
    post.subscribe(response => {
      const message = response;
      }
    )
  };


  addNewVideoLink() {
    this.linkNumber++; 
    this.links.push("link"+this.linkNumber)
  }
}


