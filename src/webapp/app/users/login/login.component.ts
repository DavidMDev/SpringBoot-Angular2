import {Component} from "@angular/core";
import {HttpService} from "../../http/http.service";
import {Router} from "@angular/router";
import {ToastsManager} from "ng2-toastr";

@Component({
  moduleId: module.id,
  selector: 'login',
  templateUrl: 'login.component.html',
  styleUrls: ['../users.component.css']
})

export class LoginComponent {

  constructor(private toastr: ToastsManager, private httpService: HttpService, private router: Router) {
  }

  public login(username: string, password: string) {
    this.httpService.login(username, password).then(result => {
      if (result) {
        this.router.navigate(['/']).then(() => {
          this.toastr.info('You have successfully logged in.');
        });
      }
    }).catch(error => {
      this.toastr.error(error);
    });
  }
}
