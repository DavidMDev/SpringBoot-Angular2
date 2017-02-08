package com.web.atrio.configuration;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import com.web.atrio.repository.AccountRepository;

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
		return tokens.get(sessionId);
	}
	
	public static void deleteToken(HttpServletRequest request){
		String sessionId = request.getSession().getId();
		request.getSession().invalidate();
		tokens.remove(sessionId);
	}

}