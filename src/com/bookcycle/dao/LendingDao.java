package com.bookcycle.dao;

import java.util.List;

import com.bookcycle.domain.Lending;

public interface LendingDao {

	public int saveLending(Lending lending);
	
	public boolean updateLending(Lending lending);
	
	public Lending findLendingById(int id);
	
	public List<Lending> findAllLending();
	
	public boolean deleteLending(int id);

	public boolean updateLending1(Lending lending1);
}
