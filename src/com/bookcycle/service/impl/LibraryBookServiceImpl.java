package com.bookcycle.service.impl;

import java.util.List;

import com.bookcycle.dao.LibraryBookDao;
import com.bookcycle.dao.impl.LibraryBookDaoImpl;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.LibraryBook;
import com.bookcycle.service.LibraryBookService;

public class LibraryBookServiceImpl extends DatabaseDaoImpl implements LibraryBookService{

	LibraryBookDao dao = new LibraryBookDaoImpl();
	
	@Override
	public int saveLibraryBook(LibraryBook librarybook) {
		// TODO Auto-generated method stub
		return dao.saveLibraryBook(librarybook);
	}

	@Override
	public boolean updateLibraryBook(LibraryBook librarybook) {
		// TODO Auto-generated method stub
		return dao.updateLibraryBook(librarybook);
	}

	@Override
	public LibraryBook findLibraryBookById(int id) {
		// TODO Auto-generated method stub
		return dao.findLibraryBookById(id);
	}

	@Override
	public List<LibraryBook> findAllLibraryBook() {
		// TODO Auto-generated method stub
		return dao.findAllLibraryBook();
	}

	@Override
	public boolean deleteLibrary(int id) {
		// TODO Auto-generated method stub
		return dao.deleteLibraryBook(id);
	}

	@Override
	public boolean updateLibraryBookStatus(LibraryBook librarybook, int status) {
		// TODO Auto-generated method stub
		return dao.updateLibraryBookStatus(librarybook,status);
	}

	@Override
	public List<LibraryBook> findAllBookByLibraryId(int lid) {
		// TODO Auto-generated method stub
		return dao.findAllBookByLibraryId(lid);
	}

}
