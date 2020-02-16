package com.bookcycle.service.impl;

import java.util.List;

import com.bookcycle.dao.BookRequestDao;
import com.bookcycle.dao.impl.BookRequestDaoImpl;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.BookRequest;
import com.bookcycle.service.BookRequestService;

public class BookRequestServiceImpl extends DatabaseDaoImpl implements BookRequestService{

	BookRequestDao dao =new BookRequestDaoImpl();
	
	@Override
	public int saveBookRequest(BookRequest bookRequest) {
		// TODO Auto-generated method stub
		return dao.saveBookRequest(bookRequest);
	}

	@Override
	public boolean updateBookRequest(BookRequest bookRequest) {
		// TODO Auto-generated method stub
		return dao.updateBookRequest(bookRequest);
	}

	@Override
	public BookRequest findBookRequestById(int id) {
		// TODO Auto-generated method stub
		return dao.findBookRequestById(id);
	}

	@Override
	public List<BookRequest> findAllBookRequest() {
		// TODO Auto-generated method stub
		return dao.findAllBookRequest();
	}

	@Override
	public boolean deleteBookRequest(int id) {
		// TODO Auto-generated method stub
		return dao.deleteBookRequest(id);
	}

}
