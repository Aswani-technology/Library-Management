package com.bookcycle.service;

import java.util.List;

import com.bookcycle.domain.LibraryBook;

public interface LibraryBookService {

	public int saveLibraryBook(LibraryBook librarybook);
	
	public boolean updateLibraryBook(LibraryBook librarybook);
	
	public LibraryBook findLibraryBookById(int id);
	
	public boolean updateLibraryBookStatus(LibraryBook librarybook, int status);
	
	public List<LibraryBook> findAllLibraryBook();
	
	public boolean deleteLibrary(int id);
	
	public List<LibraryBook> findAllBookByLibraryId(int lid);
}
