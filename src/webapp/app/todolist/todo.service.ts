import {Injectable} from "@angular/core";
import {Task} from "./task";
import {Http, Headers} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {environment} from "../../environments/environment";

@Injectable()
export class TodoService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private tasksUrl = environment.apiHost + "/api/tasks";

  constructor(private http: Http) {
}

  getTasks(): Promise<Task[]> {
    return this.http.get(this.tasksUrl)
      .toPromise()
      .then(res => <Task[]>(res.json())
      ).catch(this.handleError);
  }

  delete(id: number){
    const url = `${this.tasksUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(res => <Task[]>(res.json()))
      .catch(this.handleError);
  }

  create(name: string, description: string): Promise<Task[]> {
    return this.http
      .post(this.tasksUrl, JSON.stringify({name: name, description: description}), {headers: this.headers})
      .toPromise()
      .then(res => <Task[]>(res.json()))
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  public getTask(id: number){
    const url = `${this.tasksUrl}/${id}`;
    return this.http.get(url, {headers: this.headers})
      .toPromise()
      .then(res => <Task>(res.json()))
      .catch(this.handleError);
  }

  public modifyTask(task: Task){
    const url = `${this.tasksUrl}`;
    return this.http.put(url, JSON.stringify(task), {headers : this.headers})
      .toPromise()
      .then(res => <Task>(res.json()))
      .catch(this.handleError);
  }
}
