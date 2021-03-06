package com.bookcycle.service;

import java.util.List;

import com.bookcycle.domain.UserBook;

public interface UserBookService {

	public int saveUserBook(UserBook userbook);
	
	public boolean updateUserBook(UserBook userbook);
	
	public UserBook findUserBookById(int id);
	
	public List<UserBook> findAllUserBook();
	
	public boolean deleteUserBook(int id);
	
	public UserBook findUserBookByName(String name);
	
	public List<UserBook> findUserBookByUser(int id);
}
