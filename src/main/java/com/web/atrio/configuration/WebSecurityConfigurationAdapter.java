package com.web.atrio.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.atrio.routes.models.Route;

@EnableWebSecurity
@Configuration
public class WebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
	private static CSRFCustomRepository csrfRepository = new CSRFCustomRepository();

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		List<Route> routes = ConfigurationAccessor.getRoutes();
		for (Route route : routes) {
			for (String role : route.getPermissions()) {
				if (!role.equals("NONE")) {
					http.authorizeRequests().antMatchers(route.getMethod(), route.getUrl()).hasAuthority(role).and()
							.csrf().csrfTokenRepository(csrfRepository);
				}
			}
		}
		// Set up basic auth to obtain token
		http.antMatcher("/api/token").httpBasic().and().authorizeRequests().anyRequest();
		// Require csrf token authentication for anything else
		http.csrf().csrfTokenRepository(csrfRepository);
		http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/public/**").permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		List<Route> routes = ConfigurationAccessor.getRoutes();
		for (Route route : routes) {
			for (String role : route.getPermissions()) {
				if (role.equals("NONE")) {
					// Remove any requirements for routes with no roles (public)
					web.ignoring().antMatchers(route.getMethod(), route.getUrl());
				}
			}
		}
		 web
         .ignoring()
            .antMatchers("/public/**");
	}
}