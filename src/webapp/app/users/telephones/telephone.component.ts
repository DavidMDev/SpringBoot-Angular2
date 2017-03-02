import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ToastsManager} from "ng2-toastr";
import {TelephoneService} from "../services/telephones.service";
import {Telephone} from "./telephone";

@Component({
    moduleId: module.id,
    selector: 'phone-manager',
    templateUrl: 'telephone.component.html',
    styleUrls: ['../users.component.css']
})

export class TelephoneComponent implements OnInit {
    private sub: any;
    private telephones: Array<Telephone>;

    ngOnInit(): void {
        this.telephones = [];
        this.sub = this.route.params.subscribe(() => {
            this.telephoneService.getTelephones().then(telephones => {
                this.telephones = telephones;
            });
        });
    }

    ngOnDestroy(): void {
        this.sub.unsubscribe();
        this.telephones = null;
    }
    constructor(private toastr: ToastsManager, private telephoneService: TelephoneService, private route: ActivatedRoute) {
    }

}