
import {Routes, RouterModule} from "@angular/router";
import {TodoComponent} from "./todolist/todo.component";
import {NgModule} from "@angular/core";
import {HelloworldComponent} from "./helloworld/helloworld.component";
import {TodoDetailComponent} from "./todolist/todo-detail.component";

const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full'},
  { path: 'list', component: TodoComponent },
  { path: 'helloworld', component: HelloworldComponent },
  { path: 'detail/:id', component: TodoDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}