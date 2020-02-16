package com.bookcycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookcycle.dao.AdminDao;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.Admin;
import com.bookcycle.util.Constants;

public class AdminDaoImpl extends DatabaseDaoImpl implements AdminDao{

	Connection connection = null;
	
	PreparedStatement statement = null;
	
	@Override
	public List<Admin> findAllAdmin() {
		// TODO Auto-generated method stub
		
		List<Admin> objectList = new ArrayList<Admin>();
		Admin admin  = new Admin();
		ResultSet resultset = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from admin;");
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				String username = resultset.getString(2);
				String password = resultset.getString(3);
				
				admin = new Admin(username,password);
				
				objectList.add(admin);
				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
		}
		catch(SQLException e)
		{
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in data finding");
			e.printStackTrace();
		}
		closeDBResource();
		return objectList;
	}

}
