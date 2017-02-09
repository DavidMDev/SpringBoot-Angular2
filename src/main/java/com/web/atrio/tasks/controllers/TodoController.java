package com.web.atrio.tasks.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.atrio.tasks.models.Task;

@RestController
public class TodoController {

	private static ArrayList<Task> tasks = new ArrayList<Task>();
	private static int idCounter = 0;

	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> getTasks() {
		System.out.println("Tasks requested!");
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	@RequestMapping(value = "/tasks", method = RequestMethod.POST)
	public ResponseEntity<List<Task>> addTask(@RequestBody Task task) {
		idCounter++;
		task.setId(idCounter);
		tasks.add(task);
		System.out.println("Task added!");
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
	public ResponseEntity<Task> getTask(@PathVariable(value = "id") int id){
		Task result = null;
		for(Task task: tasks){
			if(task.getId() == id){
				result = task;
			}
		}
		System.out.println("Task requested!");
		return new ResponseEntity<Task>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<Task>> deleteTask(@PathVariable(value = "id") int id) {
		Task toDelete = null;
		for(Task task : tasks){
			if(task.getId() == id){
				toDelete = task;
			}
		}
		if(toDelete != null){
			tasks.remove(toDelete);
		}
		System.out.println("Task deleted!");
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tasks", method = RequestMethod.PUT)
	public ResponseEntity<Task> updateTask(@RequestBody Task toUpdate){
		for(Task task : tasks){
			if(task.getId() == toUpdate.getId()){
				task.setDescription(toUpdate.getDescription());
				task.setName(toUpdate.getName());
				return new ResponseEntity<Task>(task, HttpStatus.CREATED);
			}
		}
		System.out.println("Task updated!");
		return new ResponseEntity<Task>(toUpdate, HttpStatus.NOT_FOUND);
	}
}
