package com.web.atrio.configuration;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.web.atrio.routes.models.Route;
import com.web.atrio.routes.repositories.RouteRepository;

@Component
@Configuration
public class CsrfTokenFilter extends OncePerRequestFilter {
	private static final String FULL_AUTHENTICATION_REQUIRED = "Full authentication is required to access this resource";
	private static final String REQUEST_ATTRIBUTE_NAME = "_csrf";
	private static final String RESPONSE_HEADER_NAME = "X-CSRF-HEADER";
	private static final String RESPONSE_PARAM_NAME = "X-CSRF-PARAM";
	private static final String RESPONSE_TOKEN_NAME = "X-XSRF-TOKEN";
	private static final String BASIC_AUTH_HEADER_NAME = "Authorization";

	@Autowired
	RouteRepository routeRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String method = request.getMethod();
		String url = request.getServletPath();
		String auth = request.getHeader(BASIC_AUTH_HEADER_NAME);

		if(method.equals("OPTIONS")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if (checkUrl(url, method)) {
			filterChain.doFilter(request, response);
			return;
		}

		if (auth == null) {
			HttpSession session = request.getSession(false);

			if (session == null) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN,
						FULL_AUTHENTICATION_REQUIRED);
				return;
			}

			String tokenString = request.getHeader(RESPONSE_TOKEN_NAME);

			if (tokenString == null) {
				// Delete any tokens linked to the session - automatic log out
				CSRFCustomRepository.deleteToken(request);
				response.sendError(HttpServletResponse.SC_FORBIDDEN,
						FULL_AUTHENTICATION_REQUIRED);
				return;
			} else {
				CsrfToken storedToken = CSRFCustomRepository.obtainToken(request);

				if (storedToken != null && storedToken.getToken().equals(tokenString)) {
					filterChain.doFilter(request, response);
					return;
				} else {
					CSRFCustomRepository.deleteToken(request);
					response.sendError(HttpServletResponse.SC_FORBIDDEN,
							FULL_AUTHENTICATION_REQUIRED);
					return;
				}
			}
		} else {
			filterChain.doFilter(request, response);
			return;
		}
	}

	private boolean checkUrl(String url, String method) {
		Iterable<Route> routes = this.getPublicRoutes();
		
		boolean check = false;
		for (Route route : routes) {
			HttpMethod httpMethod = ConfigurationAccessor.getHttpMethodFromString(method);
			
			String routeUrl = route.getUrl();
			if (httpMethod.equals(route.getMethod()) && !check) {

				if (hasOneAsterisk(routeUrl) && !check) {
					check = compareWithOneAsterisk(routeUrl, url);
				}
				if (hasTwoAsterisks(routeUrl) && !check) {
					check = compareWithTwoAsterisks(routeUrl, url);
				}
				if (!check) {
					check = routeUrl.equals(url);
				}
			}
		}
		return check;
	}

	private Iterable<Route> getPublicRoutes() {
		Iterable<Route> routes = this.routeRepository.findAll();
		ArrayList<Route> publicRoutes = new ArrayList<Route>();
		for(Route route : routes){
			String[] roles = route.getPermissions();
			for(String role : roles) {
				if(role.equals("NONE")) {
					publicRoutes.add(route);
				}
			}
		}
		
		return publicRoutes;
	}

	private boolean compareWithOneAsterisk(String routeUrl, String url) {
		routeUrl = routeUrl.substring(0, routeUrl.length() - 1);
		String[] routeUrls = routeUrl.split("/");
		String[] ourUrls = url.split("/");
		if (ourUrls.length >= routeUrls.length) {
			boolean check = true;
			for (int i = 0; i < routeUrls.length; i++) {
				if (check) {
					check = routeUrls[i].equals(ourUrls[i]);
				}
			}
			return check;
		}
		return false;
	}

	private boolean compareWithTwoAsterisks(String routeUrl, String url) {
		routeUrl = routeUrl.substring(0, routeUrl.length() - 2);
		String[] routeUrls = routeUrl.split("/");
		String[] ourUrls = url.split("/");
		if (ourUrls.length >= routeUrls.length) {
			boolean check = true;
			for (int i = 0; i < routeUrls.length; i++) {
				if (check) {
					check = routeUrls[i].equals(ourUrls[i]);
				}
			}
			return check;
		}
		return false;
	}

	private boolean hasTwoAsterisks(String str) {
		return str.matches("^[^*]*(?:\\*[^*]*){2}$");
	}

	private boolean hasOneAsterisk(String str) {
		return str.matches("^[^*]*(?:\\*[^*]*){1}$");
	}

}