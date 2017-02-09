package com.web.atrio.configuration;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.web.atrio.routes.models.Route;

@Component
@Configuration
public class CsrfTokenFilter extends OncePerRequestFilter {
	private static final String REQUEST_ATTRIBUTE_NAME = "_csrf";
	private static final String RESPONSE_HEADER_NAME = "X-CSRF-HEADER";
	private static final String RESPONSE_PARAM_NAME = "X-CSRF-PARAM";
	private static final String RESPONSE_TOKEN_NAME = "X-XSRF-TOKEN";
	private static final String BASIC_AUTH_HEADER_NAME = "Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String method = request.getMethod();
		String url = request.getServletPath();
		try {
			List<Route> routes = ConfigurationAccessor.getPublicRoutes();
			for (Route route : routes) {
				if (route.getMethod().toString().equals(method) && url.equals(route.getUrl())) {
					filterChain.doFilter(request, response);
					return;
				}
			}

		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		String auth = request.getHeader(BASIC_AUTH_HEADER_NAME);
		if (auth == null) {
			HttpSession session = request.getSession(false);
			if(session == null){
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
						"Full authentication is required to access this resource");
				return;
			}
			String tokenString = request.getHeader(RESPONSE_TOKEN_NAME);
			if (tokenString == null) {
				// Delete any tokens linked to the session - automatic log out
				CSRFCustomRepository.deleteToken(request);
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
						"Full authentication is required to access this resource");
				return;
			} else {
				// Continue with application as normal
				CsrfToken token = (CsrfToken) request.getAttribute(REQUEST_ATTRIBUTE_NAME);
				if (token != null) {
					response.setHeader(RESPONSE_HEADER_NAME, token.getHeaderName());
					response.setHeader(RESPONSE_PARAM_NAME, token.getParameterName());
					response.setHeader(RESPONSE_TOKEN_NAME, token.getToken());
				}
				filterChain.doFilter(request, response);
				return;
			}
		} else {
			filterChain.doFilter(request, response);
			return;
		}
	}
}
