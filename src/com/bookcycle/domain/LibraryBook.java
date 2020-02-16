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

public class LibraryBook {

	private int id;
	private String book_name;
	private String publisher;
	private String author;
	private BookType booktype;
	private Library library;
	private double price;
	private int status;
	private String booktype_name;
	
	public LibraryBook(int id, String book_name, String publisher, String author, Library library, double price,
			int status, String booktype_name) {
		super();
		this.id = id;
		this.book_name = book_name;
		this.publisher = publisher;
		this.author = author;
		this.library = library;
		this.price = price;
		this.status = status;
		this.booktype_name = booktype_name;
	}

	public LibraryBook(int id, String book_name, String publisher,
			String author, BookType booktype, Library library, double price,
			int status) {
		super();
		this.id = id;
		this.book_name = book_name;
		this.publisher = publisher;
		this.author = author;
		this.booktype = booktype;
		this.library = library;
		this.price = price;
		this.status = status;
	}
	
	
	
}
