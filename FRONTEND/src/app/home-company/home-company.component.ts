import { Component, OnInit } from '@angular/core';
import { Post } from '../post';
import { User } from '../user';
import { NewPost } from '../new-post';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { Company } from '../company';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DataService } from '../data.service';
import { Vote } from '../vote';
import { Advertisement } from '../advertisement';


@Component({
  selector: 'app-home-company',
  templateUrl: './home-company.component.html',
  styleUrls: ['./home-company.component.scss']
})
export class HomeCompanyComponent implements OnInit {
  constructor(private http: HttpClient, private router: Router, public dataService: DataService, private authService: AuthService) {
  }

  chosen: string = this.chosen;
  user: User;
  company: Company;
  adContent: string = this.adContent;
  adTitle: string = this.adTitle;
  userName: string;
  postedContent: string = this.postedContent;
  topics: string[] = [];
  currentTopic: string;
  advertisements = [];
  searchedAds = [];
  advertisement: Advertisement = new Advertisement(); // NewAdvertisement ?
  show: boolean;
  contentLoaded: boolean;
  toSearch: string;
  rating: number;
  votes: Vote[] = [];
  voters: number[] = [];

  postContent: string = this.postContent;
  posts = [];
  searchedPosts = [];
  post: NewPost = new NewPost();


  ngOnInit() {
    this.authService.getAuthCompany().subscribe(resp => {
      this.company = resp;
      this.userName = this.company.name;
      this.fetchAds().subscribe(ads => {
        this.advertisements = ads;
        this.searchedAds = this.advertisements;
        this.contentLoaded = true;
      });
      this.fetchPosts().subscribe(posts => {
        this.posts = posts;
        this.searchedPosts = posts;
        this.contentLoaded = true;
      });
    });
  }


  fetchAds(): Observable<Advertisement[]> {
    return this.http.get<Advertisement[]>('/api/advertisements');
  }

  // Add new advertisement
  addItem() {
    this.sendAd().subscribe(advertisement => {
      const adId = advertisement.id;
      this.fetchAds().subscribe(ads => {
        this.advertisements = ads;
        this.searchedAds = this.advertisements;
      });
      this.show = true;
      this.adContent = '';
    });
  }

  sendAd(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json;charset=UTF-8'
    });
    const options = { headers: headers };
    return this.http.post('/api/advertisements/' + this.company.id, {
      'name': this.adTitle,
      'description': this.adContent,
    },
      options);
  }

  // Remove a specific advertisement
  removeItem(i) {
    this.http.delete<void>('/api/advertisements/' + i).subscribe(() => {
      this.fetchAds().subscribe(ads => {
        this.searchedAds = ads;
      });
    });
  }

  onAdClicked(id) {
    this.router.navigate(['advertisement/' + id]);
  }


  fetchPosts(): Observable<Post[]> {
    return this.http.get<Post[]>('/api/posts');
  }


  onPostClicked(id) {
    this.router.navigate(['comment/' + id]);
  }


  onSearchClick() {
    this.searchedAds = [];
    if (this.toSearch.toUpperCase() === '' || this.toSearch === undefined) {
      this.searchedAds = this.advertisements;
    } else {
      this.advertisements.forEach(element => {
        for (let i = 0; i < element.topics.length; i++) {
          if (element.topics[i].name.toUpperCase() === (this.toSearch.toUpperCase())) {
            this.searchedAds.push(element);
          }
        }
      });
    }
  }

  onUserClicked(event) {
    const userName = event.target.id;
    if (userName === this.company.name) {
      this.router.navigate(['profile']);
    } else {
      this.router.navigate(['users/' + userName]);
    }
  }

  onVoteClicked(event) {
    const vote = new Vote();
    vote.postId = +event.target.id;
    if (event.target.getAttribute('name') === 'true') {
      vote.vote = true;
    } else {
      vote.vote = false;
    }
    vote.voterId = this.company.user.id;
    this.getVoters().subscribe(resp => {
      this.votes = resp;
      for (let i = 0; i < this.posts.length; i++) {
        if (vote.postId === +this.posts[i].id) {
          for (let j = 0; j < this.votes.length; j++) {
            if (this.votes[j].postId === +vote.postId) {
              this.voters.push(this.votes[j].voterId);
            }
          }
          if (!this.voters.some(x => x === this.company.user.id)) {
            if (vote.vote === true) {
              this.posts[i].rating++;
            } else {
              this.posts[i].rating--;
            }
            this.http.put<void>('/api/posts/update/' + vote.postId + '/' + vote.vote, {}).subscribe(() => {
              this.sendVoteToDataBase(vote);
            });
          } else {
            alert('You\'ve already voted this post!');
          }
        }
      }
      this.voters = [];
    });
  }

  // Get the voters
  getVoters(): Observable<Vote[]> {
    return this.http.get<Vote[]>('/api/votes');
  }

  sendVoteToDataBase(vote) {
    this.http.post<void>('/api/vote', {
      'postId': vote.postId,
      'voterId': vote.voterId,
      'vote': vote.vote
    }).subscribe(
      () => { });
  }
}
