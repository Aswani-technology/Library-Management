package com.bookcycle.service.impl;

import java.util.List;

import com.bookcycle.dao.AdminDao;
import com.bookcycle.dao.impl.AdminDaoImpl;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.Admin;
import com.bookcycle.service.AdminService;

public class AdminServiceImpl extends DatabaseDaoImpl implements AdminService{

	AdminDao dao = new AdminDaoImpl();
	
	@Override
	public List<Admin> findAllAdmin() {
		// TODO Auto-generated method stub
		
		return dao.findAllAdmin();
	}

}
