package com.web.atrio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.atrio.models.users.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
	public List<Address> findByPostCode(int postCode);
}
