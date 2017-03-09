import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {ToastsManager} from "ng2-toastr";
import {UserService} from "../services/users.service";

@Component({
  moduleId: module.id,
  selector: 'signup',
  templateUrl: 'signup.component.html',
  styleUrls: ['../users.component.css']
})

export class SignupComponent {

  constructor(private toastr: ToastsManager, private userService: UserService, private router: Router) {
  }

  signup(firstName: string, lastName: string, username: string, email: string, password: string, repeatPassword: string): void {
    if(password != repeatPassword){
        this.toastr.error('Passwords must be the same');
    } else {
      this.userService.createUser(firstName, lastName, username, email, password).then( result => {
        if (result) {
          this.router.navigate(['/']).then(() => {
            this.toastr.info('You have successfully signed up.');
          });
        }
      }).catch(error => {
        this.toastr.error(error);
      });
    }
  }

}
