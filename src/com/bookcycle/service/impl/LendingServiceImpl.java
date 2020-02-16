package com.bookcycle.service.impl;

import java.util.List;

import com.bookcycle.dao.LendingDao;
import com.bookcycle.dao.impl.LendingDaoImpl;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.Lending;
import com.bookcycle.service.LendingService;

public class LendingServiceImpl extends DatabaseDaoImpl implements LendingService{

	LendingDao dao =new LendingDaoImpl();
	
	@Override
	public int saveLending(Lending lending) {
		// TODO Auto-generated method stub
		return dao.saveLending(lending);
	}

	@Override
	public boolean updateLending(Lending lending) {
		// TODO Auto-generated method stub
		return dao.updateLending(lending);
	}

	@Override
	public Lending findLendingById(int id) {
		// TODO Auto-generated method stub
		return dao.findLendingById(id);
	}

	@Override
	public List<Lending> findAllLending() {
		// TODO Auto-generated method stub
		return dao.findAllLending();
	}

	@Override
	public boolean deleteLending(int id) {
		// TODO Auto-generated method stub
		return dao.deleteLending(id);
	}

	@Override
	public boolean updateLending1(Lending lending1) {
		// TODO Auto-generated method stub
		
		return dao.updateLending1(lending1); 
	}

}
