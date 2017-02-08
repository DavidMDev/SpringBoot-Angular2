package com.web.atrio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.atrio.models.users.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	public List<Account> findByLastName(String lastName);

	public Account findByUserName(String userName);

	public Account findByEmail(String email);
}
