package com.bookcycle.service.impl;

import java.util.List;

import com.bookcycle.dao.LendingReqDao;
import com.bookcycle.dao.impl.LendingReqDaoImpl;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.LendingReq;
import com.bookcycle.service.LendingReqService;

public class LendingReqServiceImpl extends DatabaseDaoImpl implements LendingReqService{

	LendingReqDao dao = new LendingReqDaoImpl();
	
	@Override
	public int saveLendingReq(LendingReq lendingReq) {
		// TODO Auto-generated method stub
		return dao.saveLendingReq(lendingReq);
	}

	@Override
	public boolean updateLendingReq(LendingReq lendingReq) {
		// TODO Auto-generated method stub
		return dao.updateLendingReq(lendingReq);
	}

	@Override
	public LendingReq findLendingReqById(int id) {
		// TODO Auto-generated method stub
		return dao.findLendingReqById(id);
	}

	@Override
	public List<LendingReq> findAllLendingReq() {
		// TODO Auto-generated method stub
		return dao.findAllLendingReq();
	}

	@Override
	public boolean deleteLendingReq(int id) {
		// TODO Auto-generated method stub
		return dao.deleteLendingReq(id);
	}

}
