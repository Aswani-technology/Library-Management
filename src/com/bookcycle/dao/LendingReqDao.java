package com.bookcycle.dao;

import java.util.List;

import com.bookcycle.domain.LendingReq;

public interface LendingReqDao {

	public int saveLendingReq(LendingReq lendingReq);
	
	public boolean updateLendingReq(LendingReq lendingReq);
	
	public LendingReq findLendingReqById(int id);
	
	public List<LendingReq> findAllLendingReq();
	
	public boolean deleteLendingReq(int id);
}
