package com.web.atrio;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.web.atrio.configuration.ConfigurationAccessor;
import com.web.atrio.routes.models.Route;
import com.web.atrio.routes.repositories.RouteRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	RouteRepository routeRepository;

	@Autowired
	public DataLoader(RouteRepository routeRepository) {
		this.routeRepository = routeRepository;
	}

	public void run(ApplicationArguments args) throws ConfigurationException {

		Long size = this.routeRepository.count();
		if (size == 0) {
			List<Route> routes = ConfigurationAccessor.getRoutes();
			this.routeRepository.save(routes);
		}
	}
}
