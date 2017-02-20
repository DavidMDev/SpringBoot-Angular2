import {Injectable} from "@angular/core";
import {User} from "./User";

import 'rxjs/add/operator/toPromise';
import {HttpService} from "../http/http.service";
import {Telephone} from "./telephone";
import {Address} from "./address";

@Injectable()
export class UserService {
  private usersUrl = "users/";

  constructor(private httpService: HttpService) {
  }

  getUsers(): Promise<User[]> {
    return this.httpService.get(this.usersUrl)
      .then(res => {
          <User[]>(res.json());
        }
      ).catch(this.handleError);
  }

  deleteUser(id: number) {
    const url = `${this.usersUrl}/${id}`;
    return this.httpService.delete(url)
      .then(res => <User[]>(res.json()))
      .catch(this.handleError);
  }

  createUser(name: string, description: string): Promise<User[]> {
    return this.httpService
      .post(this.usersUrl, JSON.stringify({name: name, description: description}))
      .then(res => <User[]>(res.json()))
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  public getUser(id: number) {
    const url = `${this.usersUrl}/${id}`;
    return this.httpService.get(url)
      .then(res => <User>(res.json()))
      .catch(this.handleError);
  }

  public modifyUser(user: User) {
    const url = `${this.usersUrl}`;
    return this.httpService.put(url, JSON.stringify(user))
      .then(res => <User>(res.json()))
      .catch(this.handleError);
  }

  public getMyProfile() {
    const url = `me`;
    return new Promise((resolve, reject) => {
      this.httpService.get(url)
        .then(res => {
          console.log('user : ', res.json());
          let obj = res.json();
          let user = new User();
          user.username = obj.userName;
          user.firstName = obj.firstName;
          user.lastName = obj.lastName;
          user.email = obj.email;
          user.id = obj.id;
          user.roles = obj.roles;
          let telephones = Array<Telephone>();
          let addresses = Array<Address>();
          user.telephones = telephones;
          user.addresses = addresses;
          resolve(user);
        }).catch(reject);
    })
      .catch(this.handleError);
  }
}