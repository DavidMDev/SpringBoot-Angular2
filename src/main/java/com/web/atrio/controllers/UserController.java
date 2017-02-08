package com.web.atrio.controllers;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.atrio.repository.AccountRepository;

@RestController
public class UserController {

	@Autowired
	AccountRepository accountRepository;
	
	@RequestMapping("/token")
	  public Map<String,String> token(HttpSession session) {
	    return Collections.singletonMap("token", session.getId());
	  }
}
