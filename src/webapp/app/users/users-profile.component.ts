import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {User} from "./user";
import {UserService} from "./users.service";
import {Location}               from '@angular/common';

@Component({
  moduleId: module.id,
  selector: 'users',
  templateUrl: 'users-profile.component.html',
  styleUrls: ['./users.component.css']
})

export class UsersComponent implements OnInit {
  private sub: any;
  private user: User;


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (this.location.path() === 'profile/me') {
        this.usersService.getMyProfile().then(user => {
          this.user = user;
        }).catch(result => {
          console.log(result);
        });
      } else {
        this.usersService.getUser(+params['id']).then(user => {
          this.user = user;
        }).catch(result => {
          console.log(result);
        });
      }
    });
  }

  constructor(private usersService: UserService, private route: ActivatedRoute, private location: Location) {
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
    this.user = null;
  }
}
