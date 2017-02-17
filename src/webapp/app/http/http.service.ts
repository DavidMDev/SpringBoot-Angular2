import {Http, Headers} from "@angular/http";
import {Injectable} from "@angular/core";
import {User} from "../users/user";
import {Router} from "@angular/router";

@Injectable()
export class HttpService{
  private contentTypeHeader = 'Content-Type';
  private contentTypeValue = 'application/json';
  private csrfHeader = 'X-CSRF-TOKEN';
  private user : User = new User();


  private headers = new Headers();

  constructor(private http: Http, private router: Router) {
    this.headers.set(this.contentTypeHeader, this.contentTypeValue);
  }

  get(url: string) {
    this.headers.set(this.csrfHeader, this.user.token);
    return new Promise((resolve, reject) => {
      this.http.get(url).toPromise().then(result => {
        resolve(result);
      }).catch(error => {
        this.handleError(reject, error);
      });
    });
  }

  put(url: string, obj : Object){
    this.headers.set(this.csrfHeader, this.user.token);
    return new Promise((resolve, reject) => {
      this.http.put(url, JSON.stringify(obj), {headers : this.headers})
        .toPromise().then(result => {
          resolve(result);
      }).catch(error => {
        this.handleError(reject, error);
      });
    });
  }

  post(url: string, obj : Object){
    this.headers.set(this.csrfHeader, this.user.token);
    return new Promise((resolve, reject) => {
      this.http.post(url, JSON.stringify(obj), {headers : this.headers})
        .toPromise().then(result => {
        resolve(result);
      }).catch(error => {
        this.handleError(reject, error);
      });
    });
  }

  delete(url: string) {
    this.headers.set(this.csrfHeader, this.user.token);
    return new Promise((resolve, reject) => {
      this.http.delete(url).toPromise().then(result => {
        resolve(result);
      }).catch(error => {
        this.handleError(reject, error);
      });
    });
  }

  private handleError(reject, error) {
    if(error.status === 403){
      console.log(true);
      this.router.navigate(['/login']).then(() => {
        reject(error);
      });
    }
  }
}
