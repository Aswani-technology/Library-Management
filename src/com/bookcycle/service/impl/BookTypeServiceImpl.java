package com.bookcycle.service.impl;

import java.util.List;

import com.bookcycle.dao.BookTypeDao;
import com.bookcycle.dao.impl.BookTypeDaoImpl;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.BookType;
import com.bookcycle.service.BookTypeService;

public class BookTypeServiceImpl extends DatabaseDaoImpl implements BookTypeService{

	
	BookTypeDao dao= new BookTypeDaoImpl();
	
	@Override
	public int saveBookType(BookType bookType) {
		// TODO Auto-generated method stub
		return dao.saveBookType(bookType);
	}

	@Override
	public boolean updateBookType(BookType bookType) {
		// TODO Auto-generated method stub
		return dao.updateBookType(bookType);
	}

	@Override
	public BookType findBookTypeById(int id) {
		// TODO Auto-generated method stub
		return dao.findBookTypeById(id);
	}

	@Override
	public List<BookType> findAllBooktype() {
		// TODO Auto-generated method stub
		return dao.findAllBookType();
	}

	@Override
	public boolean deleteBookType(int id) {
		// TODO Auto-generated method stub
		return dao.deleteBookType(id);
	}

}
