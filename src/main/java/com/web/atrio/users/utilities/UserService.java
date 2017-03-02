package com.web.atrio.users.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.web.atrio.users.models.Account;
import com.web.atrio.users.repositories.AccountRepository;

public class UserService {
	@Autowired
	private static AccountRepository accountRepository;

	public static Account getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name;
		name = auth.getName();
		Account userLoggedIn = accountRepository.findByUsername(name);
		return userLoggedIn;
	}
}
