package com.web.atrio.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

	@RequestMapping("/hello")
	public ResponseEntity sayHello(@RequestParam(value="name", defaultValue="Bob") String name){
		return new ResponseEntity<String>("Hello " + name + ", the app is working!", HttpStatus.OK);
	}
  }
