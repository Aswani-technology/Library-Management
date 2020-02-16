package com.bookcycle.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class Librarian {

	private int id;
	private String first_name;
	private String last_name;
	private long contact;
	private String email;
	private String password;
	private String address;
	private int pincode;
	private String district;
	private int status;
	private Library library;
	public Librarian(int id, String first_name, String last_name, long contact,
			String email, String address, int pincode, String district) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.contact = contact;
		this.email = email;
		this.address = address;
		this.pincode = pincode;
		this.district = district;
	}
	
	
}


