import {Telephone} from "./telephone";
import {Address} from "./address";
export class User{
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  telephones: Array<Telephone>;
  addresses: Array<Address>;
  roles: Array<string>;
}
