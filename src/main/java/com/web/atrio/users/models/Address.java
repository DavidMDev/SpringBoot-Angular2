package com.web.atrio.users.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private int houseNumber;
	@NotNull
	private String streetName;
	private String addresDetails;
	@NotNull
	private int postCode;

	@ManyToOne
	@JoinColumn(name = "user")
	private Account user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getAddresDetails() {
		return addresDetails;
	}

	public void setAddresDetails(String addresDetails) {
		this.addresDetails = addresDetails;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public Address(int houseNumber, String streetName, String addresDetails, int postCode) {
		super();
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.addresDetails = addresDetails;
		this.postCode = postCode;
	}

	public Address() {
		super();
	}
}
