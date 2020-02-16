package com.bookcycle.service;

import java.util.List;

import com.bookcycle.domain.Librarian;

public interface LibrarianService {

public int saveLibrarian(Librarian librarian);
	
	public boolean updateLibrarian(Librarian librarian);
	
	public Librarian findLibrarianById(int id);
	
	public List<Librarian> findAllLibrarian();
	
	public boolean deleteLibrarian(int id);
}
