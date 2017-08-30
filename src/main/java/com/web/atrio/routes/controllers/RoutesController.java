package com.web.atrio.routes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.atrio.routes.models.Route;
import com.web.atrio.routes.repositories.RouteRepository;
import com.web.atrio.users.repositories.AccountRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/routes")
public class RoutesController {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	RouteRepository routeRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Route>> getRoutes() {
		return new ResponseEntity(routeRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{routeId}", method = RequestMethod.GET)
	public  ResponseEntity<Route> getRoute(@PathVariable("routeId") Long routeId){
		Route route = routeRepository.findOne(routeId);
		return new ResponseEntity<Route>(route, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{routeId}", method = RequestMethod.PUT)
	public  ResponseEntity<Route> updateRoute(@RequestBody Route route){
		Route savedRoute = routeRepository.save(route);
		return new ResponseEntity<Route>(savedRoute, HttpStatus.OK);
	}
}
