package com.web.atrio.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.atrio.exceptions.NotFoundException;
import com.web.atrio.exceptions.UnauthorizedException;
import com.web.atrio.users.models.Account;
import com.web.atrio.users.models.Address;
import com.web.atrio.users.repositories.AccountRepository;
import com.web.atrio.users.repositories.AddressRepository;
import com.web.atrio.users.utilities.UserService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
	
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	AccountRepository accountRepository;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Address> createAddress(@RequestBody Address address) {
		Account userLoggedIn = UserService.getUser();
		address.setUser(userLoggedIn);
		addressRepository.save(address);

		return new ResponseEntity<Address>(address, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{addressId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAddress(@PathVariable(value = "addressId") Long addressId)
			throws UnauthorizedException {
		Address address = addressRepository.findOne(addressId);
		Account user = address.getUser();
		Account userLoggedIn = UserService.getUser();

		if (!user.equals(userLoggedIn) && !userLoggedIn.getRoles().contains("ADMIN")) {
			throw new UnauthorizedException();
		}
		if (address != null) {
			addressRepository.delete(address);
		}
		return new ResponseEntity<String>("Address deleted", HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Address> updateAddress(@RequestBody Address address) throws UnauthorizedException {
		Address addressFromDatabase = addressRepository.findOne(address.getId());

		Account user = addressFromDatabase.getUser();
		Account userLoggedIn = UserService.getUser();

		// Allowed to modify a address if it is yours or you are an admin
		if (user.equals(userLoggedIn) || userLoggedIn.getRoles().contains("ADMIN")) {
			address = addressRepository.save(address);
			return new ResponseEntity<Address>(address, HttpStatus.OK);
		} else {
			throw new UnauthorizedException();
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List> getAddresss() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Account user = accountRepository.findByUsername(name);
		// Return the addresss of the user who sent the request
		return new ResponseEntity<List>((List) user.getAddresses(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
	public ResponseEntity<Address> getAddress(@PathVariable(value = "addressId") Long addressId)
			throws NotFoundException, UnauthorizedException {
		Address address = addressRepository.findOne(addressId);
		if (address == null) {
			throw new NotFoundException();
		}
		Account user = address.getUser();
		Account userLoggedIn = UserService.getUser();
		// Allow to view address if you're an admin or it is your address
		if ((user.equals(userLoggedIn)) || userLoggedIn.getRoles().contains("ADMIN")) {
			return new ResponseEntity<Address>(address, HttpStatus.OK);
		} else {
			throw new UnauthorizedException();
		}
	}
}
