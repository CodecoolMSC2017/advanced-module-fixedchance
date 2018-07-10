import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { DataService } from '../data.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user : User

  constructor(private dataService : DataService) { }

  ngOnInit() {
    this.user = this.dataService.user;
  }

}
