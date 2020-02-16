package com.bookcycle.service;

import java.util.List;

import com.bookcycle.domain.BookType;

public interface BookTypeService {

	public int saveBookType(BookType bookType);
	
	public boolean updateBookType(BookType bookType);
	
	public BookType findBookTypeById(int id);
	
	public List<BookType> findAllBooktype();
	
	public boolean deleteBookType(int id);
}
