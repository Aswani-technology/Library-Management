package com.bookcycle.dao;

import java.util.List;

import com.bookcycle.domain.Library;
import com.bookcycle.domain.User;

public interface LibraryDao {

	public int saveLibrary(Library library);
	
	public boolean updateLibrary(Library library);
	
	public Library findLibraryById(int id);
	
	public List<Library> findAllLibrary();
	
	public boolean deleteLibrary(int id);
	
	public Library findLibraryByUser(User user);
	
	public List<Library> findLibraryByPin(String dst);
}
