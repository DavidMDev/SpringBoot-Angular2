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
  private user: User;
  private sub: any;



  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      let id = +params['id'];
      if (id === 0) {
        this.usersService.getMyProfile().then(user => {
          this.user = user;
        }).catch(result => {
          console.log(result);
        });
      } else {
        this.usersService.getUser(id).then(user => {
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
