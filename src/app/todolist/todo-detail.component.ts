import { Task } from './Task';
import {OnInit, Component, OnDestroy} from "@angular/core";
import {TodoService} from "./todo.service";
import {ActivatedRoute} from "@angular/router";
import { Location }               from '@angular/common';

@Component({
  moduleId: module.id,
  selector: 'todo-detail',
  templateUrl: './todo-detail.component.html',
  styleUrls: [ './todo.component.css' ]
})

export class TodoDetailComponent implements OnInit, OnDestroy {
  private task: Task;
  private sub: any;
  constructor(private todoService: TodoService, private route: ActivatedRoute, private location: Location) {}

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.todoService.getTask(+params['id']).then(task => {
        this.task = task;
      });
    })
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
    this.task = null;
  }

  public save(): void{
    this.todoService.modifyTask(this.task).then(task => {
      this.task = task;
    })
  }

  goBack(): void {
    this.location.back();
  }
}
