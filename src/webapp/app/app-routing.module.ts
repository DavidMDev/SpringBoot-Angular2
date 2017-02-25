
import {Routes, RouterModule} from "@angular/router";
import {TodoComponent} from "./todolist/todo.component";
import {NgModule} from "@angular/core";
import {HelloworldComponent} from "./helloworld/helloworld.component";
import {TodoDetailComponent} from "./todolist/todo-detail.component";
import {UsersComponent} from "./users/profile/users-profile.component";
import {LoginComponent} from "./users/login/login.component";
import {AppComponent} from "./app.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full', component: HomeComponent},
  { path: 'list', component: TodoComponent },
  { path: 'helloworld', component: HelloworldComponent },
  { path: 'detail/:id', component: TodoDetailComponent },
  { path: 'profile/:id', component: UsersComponent },
  { path: 'profile/me', component: UsersComponent},
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
