import {Component, Input, ViewContainerRef} from '@angular/core';
import {Http, Response} from '@angular/http';
import {HttpService} from "./http/http.service";
import {ToastsManager} from "ng2-toastr";
import {environment} from "../environments/environment";
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
})
export class AppComponent {
  messageUrl: string;
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
      this.messageUrl = environment.apiHost + "/hello?name=" + name;
    } else {
      this.messageUrl = environment.apiHost + "/hello";
    }
    this.http.get(this.messageUrl).subscribe((data: Response) => {
      this.message = data.text();
    });
  }

}
