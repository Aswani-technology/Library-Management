package com.bookcycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookcycle.dao.LendingReqDao;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.LendingReq;
import com.bookcycle.domain.LibraryBook;
import com.bookcycle.domain.User;
import com.bookcycle.service.LibraryBookService;
import com.bookcycle.service.UserService;
import com.bookcycle.service.impl.LibraryBookServiceImpl;
import com.bookcycle.service.impl.UserServiceImpl;
import com.bookcycle.util.Constants;

public class LendingReqDaoImpl extends DatabaseDaoImpl implements LendingReqDao{

	Connection connection = null;
	PreparedStatement statement = null;
	
	
	@Override
	public int saveLendingReq(LendingReq lendingReq) {
		// TODO Auto-generated method stub
		
		int key = 0;
		System.out.println(lendingReq);
		try
		{
			connection = getConnection();
			System.out.println("============User============");
			statement = connection.prepareStatement("insert into lending_req(book_id,user_id) values (?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, lendingReq.getBook().getId());
			statement.setInt(2, lendingReq.getUser().getId());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next())
			{
				System.out.println("Insertion done successfully...");
				System.out.println("Registration id = ");
				key = generatedKeys.getInt(1);
			}
			else
			{
				System.out.println("Registration id = not found ");
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error occured in insertion");
			e.printStackTrace();
		}
		closeDBResource();
		return key;
	}

	@Override
	public boolean updateLendingReq(LendingReq lendingReq) {
		// TODO Auto-generated method stub
		
		boolean is_update = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("update lending_req set book_id=?,user_id=? where id=?;");
			statement.setInt(1, lendingReq.getBook().getId());
			statement.setInt(2, lendingReq.getUser().getId());
			statement.setInt(3, lendingReq.getId());
			int check_id = statement.executeUpdate();
			is_update = check_id>0;
			if(is_update)
			{
				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
			else
			{
				Constants.Response.MSG = Constants.Response.MSG_FAILED;
				System.out.println("Updation Failed");
			}
		}
		catch(SQLException e)
		{
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in insertion");
			e.printStackTrace();
		}
		closeDBResource();
		return is_update;
	}

	@Override
	public LendingReq findLendingReqById(int id) {
		// TODO Auto-generated method stub
		
		LendingReq lendingReq = new LendingReq();
		
		ResultSet resultSet = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from lending_req where id=?;");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			int book_id = resultSet.getInt(2);
			LibraryBookService lib_bk_service = new LibraryBookServiceImpl();
			LibraryBook bk = lib_bk_service.findLibraryBookById(book_id);
			int user_id = resultSet.getInt(3);
			UserService us_se = new UserServiceImpl();
			User user = us_se.findUserById(user_id);
			
			lendingReq = new LendingReq(id,bk,user);
			Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
		}
		catch(SQLException e)
		{
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in finding data");
			e.printStackTrace();
		}
		closeDBResource();
		return lendingReq;
	}

	@Override
	public List<LendingReq> findAllLendingReq() {
		// TODO Auto-generated method stub
		
		List<LendingReq> objectList = new ArrayList<LendingReq>();
		LendingReq lendingReq  = new LendingReq();
		ResultSet resultSet = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from lending_req;");
			resultSet = statement.executeQuery();
			while(resultSet!=null && resultSet.next())
			{
				int id = resultSet.getInt(1);
				int book_id = resultSet.getInt(2);
				LibraryBookService lib_bk_service = new LibraryBookServiceImpl();
				LibraryBook bk = lib_bk_service.findLibraryBookById(book_id);
				int user_id = resultSet.getInt(3);
				UserService us_se = new UserServiceImpl();
				User user = us_se.findUserById(user_id);
				
				lendingReq = new LendingReq(id,bk,user);
				
				objectList.add(lendingReq);
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

	@Override
	public boolean deleteLendingReq(int id) {
		// TODO Auto-generated method stub
		boolean is_delete = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("delete from lending_req where id=?;");
			statement.setInt(1, id);
			int check_id = statement.executeUpdate();
			is_delete = check_id>0;
			if(is_delete)
			{
				System.out.println("deleted");
				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
			}
			else
			{
				Constants.Response.MSG = Constants.Response.MSG_FAILED;
			}
			System.out.println("Deletion completed Successfully");
		}
		catch(SQLException e)
		{
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in deletion");
			e.printStackTrace();
		}
		closeDBResource();
		return is_delete;
	}

}
