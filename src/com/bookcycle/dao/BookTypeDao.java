package com.bookcycle.dao;

import java.util.List;

import com.bookcycle.domain.BookType;
public interface BookTypeDao {

	public int saveBookType(BookType bookType);
	public boolean updateBookType(BookType bookType);
	public BookType findBookTypeById(int id);
	public List<BookType> findAllBookType();
	public boolean deleteBookType(int id);
	
	
}