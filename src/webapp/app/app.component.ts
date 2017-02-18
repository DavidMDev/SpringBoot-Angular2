import {Component, Input, ViewContainerRef} from '@angular/core';
import {Http, Response} from '@angular/http';
import {RouterOutlet} from "@angular/router";
import {HttpService} from "./http/http.service";
import {ToastsManager} from "ng2-toastr";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  private messageUrl: string;
  message: string;

  constructor(private toastr: ToastsManager, private vcr: ViewContainerRef, private http: Http, private httpService: HttpService) {
    this.toastr.setRootViewContainerRef(vcr);
  }


  @Input()
  name: string = "";

  sendMessage() {
    this.getMessage(this.name);
  }

  public getMessage(name) {
    if (name != "") {
      this.messageUrl = "http://localhost:8080/hello?name=" + name;
    } else {
      this.messageUrl = "http://localhost:8080/hello";
    }
    this.http.get(this.messageUrl).subscribe((data: Response) => {
      this.message = data.text();
    });
  }
}
