
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {TodoService} from "./todo.service";
import { Task } from "./task";
@Component({
  moduleId: module.id,
  selector: 'todo-list',
  templateUrl: './todo.component.html',
  styleUrls: [ './todo.component.css' ]
})
export class TodoComponent implements OnInit {
  tasks: Task[] = [];

  constructor(private router: Router, private todoService: TodoService) { }

  ngOnInit(): void {
    this.getTasks();
  }

  getTasks(): void {
    this.todoService.getTasks().then(tasks => {
      this.tasks = tasks;
    });
  }

  add(name: string, description: string): void {
    name = name.trim();
    if (!name && !description) { return; }
    this.todoService.create(name, description)
      .then(tasks => {
        this.tasks = tasks;
      });
  }

  delete(task: Task): void {
    this.todoService
      .delete(task.id)
      .then((tasks) => {
        this.tasks = tasks;
      });
  }

  goto(task: Task): void {
    this.router.navigate(['/detail', task.id]);
  }
}
