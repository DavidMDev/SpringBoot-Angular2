import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {ToastsManager} from "ng2-toastr";
import {AddressService} from "../services/address.service";
import {Address} from "./address";

@Component({
    moduleId: module.id,
    selector: 'address-manager',
    templateUrl: 'address.component.html',
    styleUrls: ['../users.component.css']
})

export class AddressComponent implements OnInit {
    private sub: any;
    private addresses: Array<Address>;

    ngOnInit(): void {
        this.addresses = [];
    }

    constructor(private toastr: ToastsManager, private addressService: AddressService, private router: Router) {
    }

}