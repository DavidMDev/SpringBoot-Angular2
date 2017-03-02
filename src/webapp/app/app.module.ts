import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { LocalStorageModule } from 'angular-2-local-storage';
import { AppComponent } from './app.component';
import { AppRoutingModule }     from './app-routing.module';
import {TodoComponent} from "./todolist/todo.component";
import {TodoService} from "./todolist/todo.service";
import {HelloworldComponent} from "./helloworld/helloworld.component";
import {TodoDetailComponent} from "./todolist/todo-detail.component";
import {UsersComponent} from "./users/profile/users-profile.component";
import {HttpService} from "./http/http.service";
import {LoginComponent} from "./users/login/login.component";
import {ToastModule} from "ng2-toastr";
import {HomeComponent} from "./home/home.component";
import {UserService} from "./users/services/users.service";
import {SignupComponent} from "./users/login/signup.component";
import {TelephoneComponent} from "./users/telephones/telephone.component";
import {TelephoneService} from "./users/services/telephones.service";
import {AddressComponent} from "./users/addresses/address.component";
import {AddressService} from "./users/services/address.service";
import {TelephoneDetailComponent} from "./users/telephones/telephone-detail.component";
import {AddressDetailComponent} from "./users/addresses/address-detail.component";

@NgModule({
  declarations: [
    TodoComponent,
    AppComponent,
    HelloworldComponent,
    TodoDetailComponent,
    UsersComponent,
    LoginComponent,
    HomeComponent,
    SignupComponent,
    TelephoneComponent,
    TelephoneDetailComponent,
    AddressComponent,
    AddressDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    AppRoutingModule,
    LocalStorageModule.withConfig({
      prefix: 'web-atrio-app',
      storageType: 'localStorage'
    }),
    ToastModule.forRoot()
  ],
  providers: [TodoService, HttpService, UserService, TelephoneService, AddressService],
  bootstrap: [AppComponent]
})
export class AppModule { }
