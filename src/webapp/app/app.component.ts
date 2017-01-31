import {Component, Input} from '@angular/core';
import {Http, Response} from '@angular/http';
import {RouterOutlet} from "@angular/router";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
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
