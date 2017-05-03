import {Component, Input} from '@angular/core';
import {Http, Response} from '@angular/http';
import {environment} from "../../environments/environment";

@Component({
  selector: 'helloworld',
  templateUrl: './helloworld.component.html',
  styleUrls: ['./helloworld.component.css'],
})
export class HelloworldComponent {
  messageUrl: string;
  message: string;

  constructor(private http: Http) {
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
