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

public class UserBook {

	private int id;
	private String book_name;
	private String publisher;
	private String author;
	private BookType booktype;
	private User user;
	private int status;
	private String bookType_name;
	
	public UserBook(int id, String book_name, String publisher, String author, String bookType_name, User user, int status) {
		super();
		this.id = id;
		this.book_name = book_name;
		this.publisher = publisher;
		this.author = author;
		this.bookType_name = bookType_name;
		this.user = user;
		this.status = status;
	}
	
	public UserBook(int id, String book_name, String publisher, String author,
			BookType booktype, User user, int status) {
		super();
		this.id = id;
		this.book_name = book_name;
		this.publisher = publisher;
		this.author = author;
		this.booktype = booktype;
		this.user = user;
		this.status = status;
	}
	
	
}
