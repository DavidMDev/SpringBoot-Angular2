package com.web.atrio.configuration;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import com.web.atrio.users.repositories.AccountRepository;

public class CSRFCustomRepository implements CsrfTokenRepository {
	private static HashMap<String, CsrfToken> tokens = new HashMap<String, CsrfToken>();
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
		CsrfToken token = CookieCsrfTokenRepository.withHttpOnlyFalse().generateToken(request);
		return token;
	}

	@Override
	public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
		String sessionId = request.getSession().getId();
		tokens.put(sessionId, token);
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
		String sessionId = request.getSession().getId();
		CsrfToken token = tokens.get(sessionId);
		String tokenFromRequest = request.getHeader("X-XSRF-TOKEN");

		if(token != null && token.getToken().equals(tokenFromRequest)){
			System.out.println(tokenFromRequest);
			System.out.println(token.getToken());
			return token;
		} else {
			tokens.remove(sessionId);
			request.getSession().invalidate();
			return null;
		}
	}
	
	public static void deleteToken(HttpServletRequest request){
		String sessionId = request.getSession().getId();
		request.getSession().invalidate();
		tokens.remove(sessionId);
	}

	public static String getTokenFromSessionId(HttpServletRequest request){
		CsrfToken token = tokens.get(request.getSession().getId());
		if(token == null){
			token = CookieCsrfTokenRepository.withHttpOnlyFalse().generateToken(request);
			tokens.put(request.getSession().getId(), token);
		}
		return token.getToken();
	}
}
