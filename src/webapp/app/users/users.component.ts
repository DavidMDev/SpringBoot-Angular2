import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {HttpService} from "../http/http.service";

@Component({
  moduleId: module.id,
  selector: 'users',
  templateUrl: './users-login.component.html',
  styleUrls: ['./users.component.css']
})

export class UsersComponent implements OnInit{
  ngOnInit(): void {
  }

  constructor(private router: Router, private httpService: HttpService) { }
  getTest(){
    let url = 'http://localhost:8080/api/users/';
    this.httpService.get(url).then(result => {
      console.log(result);
    }).catch(result => {
      console.log(result);
    });
  }
}
