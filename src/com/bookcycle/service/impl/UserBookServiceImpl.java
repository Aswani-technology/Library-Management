package com.bookcycle.service.impl;

import java.util.List;

import com.bookcycle.dao.UserBookDao;
import com.bookcycle.dao.impl.UserBookDaoImpl;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.UserBook;
import com.bookcycle.service.UserBookService;

public class UserBookServiceImpl extends DatabaseDaoImpl implements UserBookService{

	UserBookDao dao = new UserBookDaoImpl();
	
	@Override
	public int saveUserBook(UserBook userbook) {
		// TODO Auto-generated method stub
		return dao.saveUserBook(userbook);
	}

	@Override
	public boolean updateUserBook(UserBook userbook) {
		// TODO Auto-generated method stub
		return dao.updateUserBook(userbook);
	}

	@Override
	public UserBook findUserBookById(int id) {
		// TODO Auto-generated method stub
		return dao.findUserBookById(id);
	}

	@Override
	public List<UserBook> findAllUserBook() {
		// TODO Auto-generated method stub
		return dao.findAllUserBook();
	}

	@Override
	public boolean deleteUserBook(int id) {
		// TODO Auto-generated method stub
		return dao.deleteUserBook(id);
	}

	@Override
	public UserBook findUserBookByName(String name) {
		// TODO Auto-generated method stub
		return dao.findUserBookByName(name);
	}

	@Override
	public List<UserBook> findUserBookByUser(int userid) {
		// TODO Auto-generated method stub
		return dao.findUserBookByUser(userid);
	}

}
