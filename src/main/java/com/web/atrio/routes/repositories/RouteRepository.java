package com.web.atrio.routes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.atrio.routes.models.Route;

public interface RouteRepository extends CrudRepository<Route, Long>{
	public List<Route> findByUrl(String url);
}
