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

public class Library {

	private int id;
	private String name;
	private String address;
	private String pincode;
	private String district;
	private String phone;
	
}
