// core/navbar.component.ts
import { Component } from '@angular/core';
import {HttpService} from "../http/http.service";
@Component({
  selector: 'component-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent {

  constructor(private httpService: HttpService){
  }

  isIn = false;   // store state
  toggleState() { // click handler
    let bool = this.isIn;
    this.isIn = bool === false ? true : false;
  }
}
