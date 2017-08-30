package com.web.atrio.permissions.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.atrio.exceptions.UnauthorizedException;
import com.web.atrio.routes.models.Route;
import com.web.atrio.routes.repositories.RouteRepository;
import com.web.atrio.users.models.Account;
import com.web.atrio.users.repositories.AccountRepository;
import com.web.atrio.users.utilities.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/routes")
public class PermissionsController {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	RouteRepository routeRepository;
	
	@RequestMapping("/")
public ResponseEntity<List<Route>> getRoutes(HttpServletRequest request) throws UnauthorizedException{
		Account accountFromRequest = accountRepository.findByUsername(UserService.getUser(request));
		if(!accountFromRequest.getRoles().contains("ADMIN")) {
			throw new UnauthorizedException();
		}
	return new ResponseEntity(routeRepository.findAll(), HttpStatus.OK);	
	}
}
