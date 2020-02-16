
package com.bookcycle.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString

public class LendingReq {

	private int id;
	private LibraryBook book;
	private User user;
	
	public LendingReq(int id, LibraryBook book, User user) {
		super();
		this.id = id;
		this.book = book;
		this.user = user;
	}
	
	
	
}
