import {Injectable} from "@angular/core";
import {HttpService} from "../../http/http.service";
import {Address} from "../addresses/address";

@Injectable()
export class AddressService {
  private addresssUrl = "/addresses";

  constructor(private httpService: HttpService) {
  }

  getAddresss(): Promise<Address[]> {
    return this.httpService.get(this.addresssUrl)
      .then(res => {
          <Address[]>(res.json());
        }
      ).catch(this.handleError);
  }

  deleteAddress(id: number) {
    const url = `${this.addresssUrl}/${id}`;
    return this.httpService.delete(url)
      .then(res => <Address[]>(res.json()))
      .catch(this.handleError);
  }

  createAddress(name: string, description: string): Promise<Address[]> {
    return this.httpService
      .post(this.addresssUrl, JSON.stringify({name: name, description: description}))
      .then(res => <Address[]>(res.json()))
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  public getAddress(id: number) {
    const url = `${this.addresssUrl}/${id}`;
    return this.httpService.get(url)
      .then(res => <Address>(res.json()))
      .catch(this.handleError);
  }

  public modifyAddress(address: Address) {
    const url = `${this.addresssUrl}`;
    return this.httpService.put(url, JSON.stringify(address))
      .then(res => <Address>(res.json()))
      .catch(this.handleError);
  }

}
