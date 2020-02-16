package com.bookcycle.dao;

import java.util.List;

import com.bookcycle.domain.LibraryBook;

public interface LibraryBookDao {

	public int saveLibraryBook(LibraryBook librarybook);
	
	public boolean updateLibraryBook(LibraryBook librarybook);
	
	public LibraryBook findLibraryBookById(int id);
	
	public List<LibraryBook> findAllLibraryBook();
	
	public boolean deleteLibraryBook(int id);

	public boolean updateLibraryBookStatus(LibraryBook librarybook, int status);
	
	public List<LibraryBook> findAllBookByLibraryId(int lid);
	
}
