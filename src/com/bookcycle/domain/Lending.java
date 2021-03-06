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

public class Lending {

	private int id;
	private User user;
	private LibraryBook book;
	private String lending_date;
	private String return_date;
	private String returned_date;
	private int lending_status;
}
