import {Injectable} from "@angular/core";
import {HttpService} from "../../http/http.service";
import {Telephone} from "../telephones/telephone";


@Injectable()
export class TelephoneService {
  private telephoneUrl = "telephones/";

  constructor(private httpService: HttpService) {
  }

  getTelephones(): Promise<Telephone[]> {
    return this.httpService.get(this.telephoneUrl)
      .then(res => {
        console.log(res);
          <Telephone[]>(res.json());
        }
      ).catch(this.handleError);
  }

  deleteTelephone(id: number) {
    const url = `${this.telephoneUrl}${id}`;
    return this.httpService.delete(url)
      .then(res => <Telephone[]>(res.json()))
      .catch(this.handleError);
  }

  createTelephone(name: string, description: string): Promise<Telephone[]> {
    return this.httpService
      .post(this.telephoneUrl, JSON.stringify({name: name, description: description}))
      .then(res => <Telephone[]>(res.json()))
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  public getTelephone(id: number) {
    const url = `${this.telephoneUrl}/${id}`;
    return this.httpService.get(url)
      .then(res => <Telephone>(res.json()))
      .catch(this.handleError);
  }

  public modifyTelephone(telephone: Telephone) {
    const url = `${this.telephoneUrl}`;
    return this.httpService.put(url, JSON.stringify(telephone))
      .then(res => <Telephone>(res.json()))
      .catch(this.handleError);
  }

}
