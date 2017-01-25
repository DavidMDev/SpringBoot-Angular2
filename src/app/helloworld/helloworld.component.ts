import {Component, Input} from '@angular/core';
import {Http, Response} from '@angular/http';
import {RouterOutlet} from "@angular/router";
@Component({
  selector: 'helloworld',
  templateUrl: './helloworld.component.html',
  styleUrls: ['./helloworld.component.css'],
})
export class HelloworldComponent {
  private messageUrl: string;
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
      this.messageUrl = "http://localhost:8080/hello?name=" + name;
    } else {
      this.messageUrl = "http://localhost:8080/hello";
    }
    this.http.get(this.messageUrl).subscribe((data: Response) => {
      this.message = data.text();
    });
  }
}