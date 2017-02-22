package com.web.atrio.users.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.atrio.configuration.AuthenticatedUsersService;
import com.web.atrio.configuration.CSRFCustomRepository;
import com.web.atrio.exceptions.NotFoundException;
import com.web.atrio.exceptions.UnauthorizedException;
import com.web.atrio.users.models.Account;
import com.web.atrio.users.repositories.AccountRepository;

@RestController
public class UserController {

	@Autowired
	AccountRepository accountRepository;

	@RequestMapping(value = "/api/token", method = RequestMethod.GET)
	public Map<String, Object> token(HttpServletRequest request) {
		String sessionId = request.getSession().getId();
		String username = request.getRemoteUser();
		AuthenticatedUsersService.logUser(username, sessionId);
		return Collections.singletonMap("token", CSRFCustomRepository.getTokenFromSessionId(request));
	}

	@RequestMapping(value = "/api/users", method = RequestMethod.POST)
	public ResponseEntity<Account> createUser(@RequestBody Account user) {
		accountRepository.save(user);
		return new ResponseEntity<Account>(user, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/users/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") Long userId) {
		Account account = accountRepository.findOne(userId);
		if (account != null) {
			accountRepository.delete(account);
		}
		return new ResponseEntity<String>("User deleted", HttpStatus.OK);
	}

	@RequestMapping(value = "/api/users", method = RequestMethod.PUT)
	public ResponseEntity<Account> updateUser(@RequestBody Account user) throws UnauthorizedException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		if (user.getUsername().equals(name)) {
			user = accountRepository.save(user);
			return new ResponseEntity<Account>(user, HttpStatus.OK);
		} else {
			throw new UnauthorizedException();
		}
	}

	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public ResponseEntity<List> getUsers() {
		return new ResponseEntity<List>((List) accountRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/users/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Account> getUser(@PathVariable(value = "userId") Long userId) throws NotFoundException {
		Account user = accountRepository.findOne(userId);
		if (user == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<Account>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/me", method = RequestMethod.GET)
	public ResponseEntity<Account> getMe(HttpServletRequest request) {
		String username = AuthenticatedUsersService.getUser(request.getSession().getId());
		return new ResponseEntity<Account>(accountRepository.findByUsername(username), HttpStatus.OK);
	}
}
