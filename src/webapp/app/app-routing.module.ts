
import {Routes, RouterModule} from "@angular/router";
import {TodoComponent} from "./todolist/todo.component";
import {NgModule} from "@angular/core";
import {HelloworldComponent} from "./helloworld/helloworld.component";
import {TodoDetailComponent} from "./todolist/todo-detail.component";
import {UsersComponent} from "./users/users.component";
import {LoginComponent} from "./users/login.component";

const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full'},
  { path: 'list', component: TodoComponent },
  { path: 'helloworld', component: HelloworldComponent },
  { path: 'detail/:id', component: TodoDetailComponent },
  { path: 'users', component: UsersComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
