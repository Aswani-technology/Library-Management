package com.bookcycle.service.impl;

import java.util.List;

import com.bookcycle.dao.LibraryDao;
import com.bookcycle.dao.impl.LibraryDaoImpl;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.Library;
import com.bookcycle.domain.User;
import com.bookcycle.service.LibraryService;

public class LibraryServiceImpl extends DatabaseDaoImpl implements LibraryService {

	LibraryDao dao = new LibraryDaoImpl();
	
	@Override
	public int saveLibrary(Library library) {
		// TODO Auto-generated method stub
		return dao.saveLibrary(library);
	}

	@Override
	public boolean updateLibrary(Library library) {
		// TODO Auto-generated method stub
		return dao.updateLibrary(library);
	}

	@Override
	public Library findLibraryById(int id) {
		// TODO Auto-generated method stub
		return dao.findLibraryById(id);
	}

	@Override
	public List<Library> findAllLibrary() {
		// TODO Auto-generated method stub
		return dao.findAllLibrary();
	}

	@Override
	public boolean deleteLibrary(int id) {
		// TODO Auto-generated method stub
		return dao.deleteLibrary(id);
	}

	@Override
	public Library findLibraryByUser(User user) {
		// TODO Auto-generated method stub
		return dao.findLibraryByUser(user);
	}

	@Override
	public List<Library> findLibraryByPin(String dst) {
		// TODO Auto-generated method stub
		return dao.findLibraryByPin(dst);
	}

}
