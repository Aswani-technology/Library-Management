package com.bookcycle.dao;

import java.util.List;

import com.bookcycle.domain.BookRequest;

public interface BookRequestDao {

	public int saveBookRequest(BookRequest bookRequest);
	
	public boolean updateBookRequest(BookRequest bookRequest);
	
	public BookRequest findBookRequestById(int id);
	
	public List<BookRequest> findAllBookRequest();
	
	public boolean deleteBookRequest(int id);
	
}
