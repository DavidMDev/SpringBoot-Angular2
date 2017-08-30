package com.web.atrio.users.utilities;

import javax.servlet.http.HttpServletRequest;

import com.web.atrio.configuration.AuthenticatedUsersService;

public class UserService {

	public static String getUser(HttpServletRequest request) {
		String name = AuthenticatedUsersService.getUser(request.getSession(false).getId());
		return name;
	}
}
