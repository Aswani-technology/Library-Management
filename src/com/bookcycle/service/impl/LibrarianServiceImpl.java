package com.bookcycle.service.impl;

import java.util.List;

import com.bookcycle.dao.LibrarianDao;
import com.bookcycle.dao.impl.LibrarianDaoImpl;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.Librarian;
import com.bookcycle.service.LibrarianService;

public class LibrarianServiceImpl extends DatabaseDaoImpl implements LibrarianService{

	LibrarianDao dao = new LibrarianDaoImpl();
	
	@Override
	public int saveLibrarian(Librarian librarian) {
		// TODO Auto-generated method stub
		return dao.saveLibrarian(librarian);
	}

	@Override
	public boolean updateLibrarian(Librarian librarian) {
		// TODO Auto-generated method stub
		return dao.updateLibrarian(librarian);
	}

	@Override
	public Librarian findLibrarianById(int id) {
		// TODO Auto-generated method stub
		return dao.findLibrarianById(id);
	}

	@Override
	public List<Librarian> findAllLibrarian() {
		// TODO Auto-generated method stub
		return dao.findAllLibrarian();
	}

	@Override
	public boolean deleteLibrarian(int id) {
		// TODO Auto-generated method stub
		return dao.deleteLibrarian(id);
	}

}
