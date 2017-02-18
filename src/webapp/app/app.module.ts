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
import {UsersComponent} from "./users/users.component";
import {HttpService} from "./http/http.service";
import {LoginComponent} from "./users/login.component";
import {ToastModule} from "ng2-toastr";

@NgModule({
  declarations: [
    TodoComponent,
    AppComponent,
    HelloworldComponent,
    TodoDetailComponent,
    UsersComponent,
    LoginComponent
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
  providers: [TodoService, HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
