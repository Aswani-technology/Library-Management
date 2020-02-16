package com.bookcycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookcycle.dao.LendingDao;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.Lending;
import com.bookcycle.domain.LibraryBook;
import com.bookcycle.domain.User;
import com.bookcycle.service.LibraryBookService;
import com.bookcycle.service.UserService;
import com.bookcycle.service.impl.LibraryBookServiceImpl;
import com.bookcycle.service.impl.UserServiceImpl;
import com.bookcycle.util.Constants;

public class LendingDaoImpl extends DatabaseDaoImpl implements LendingDao{

	
	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int saveLending(Lending lending) {
		// TODO Auto-generated method stub
		int key = 0;
		System.out.println(lending);
		try
		{
			connection = getConnection();
			System.out.println("======================user=======================");
			
			statement = connection.prepareStatement("insert into lending(user_id,lib_book_id,lending_date,return_date,returned_date,status) values(?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, lending.getUser().getId());
			statement.setInt(2, lending.getBook().getId());
			statement.setString(3, lending.getLending_date());
			statement.setString(4, lending.getReturn_date());
			statement.setString(5, lending.getReturned_date());
			statement.setInt(6, lending.getLending_status());
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
	public boolean updateLending(Lending lending) {
		// TODO Auto-generated method stub
		
		boolean is_update = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("update lending set "+ getAllColumnName()+ "where id=?");
			statement.setInt(1, lending.getUser().getId());
			statement.setInt(2, lending.getBook().getId());
			statement.setString(3,lending.getLending_date());
			statement.setString(4, lending.getReturn_date());
			statement.setString(5, lending.getReturned_date());
			statement.setInt(6,lending.getLending_status());
			statement.setInt(7,lending.getId());
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

	private String getAllColumnName() {
		// TODO Auto-generated method stub
		
		StringBuilder builder = new StringBuilder();
		// .append(COLUMN_id).append("=?,").
		builder.append("user_id").append("=?,");
		builder.append("lib_book_id").append("=?,");
		builder.append("lending_date").append("=?,");
		builder.append("return_date").append("=?,");
		builder.append("returned_date").append("=?,");
		builder.append("status").append("=?");
		
		return builder.toString();
	}

	@Override
	public Lending findLendingById(int id) {
		// TODO Auto-generated method stub
		
		Lending lending = new Lending();
		
		ResultSet resultset = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from lending where id=?;");
			statement.setInt(1,id);
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				int user_id = resultset.getInt(2);
				UserService us_se = new UserServiceImpl();
				User user = us_se.findUserById(user_id);
				int lib_book_id = resultset.getInt(3);
				LibraryBookService lb_se = new LibraryBookServiceImpl();
				LibraryBook book = lb_se.findLibraryBookById(lib_book_id);
				String lending_date = resultset.getString(4);
				String return_date = resultset.getString(5);
				String returned_date = resultset.getString(6);
				int lending_status = resultset.getInt(7);
				
				lending = new Lending(id,user,book,lending_date,return_date,returned_date,lending_status);
				
				Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
				
			}
		}
		catch(SQLException e)
		{
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in finding data");
			e.printStackTrace();
		}
		closeDBResource();
		return lending;
	}

	@Override
	public List<Lending> findAllLending() {
		// TODO Auto-generated method stub
		
		List<Lending> objectList = new ArrayList<Lending>();
		Lending lending = new Lending();
		ResultSet resultset = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from lending;");
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				int id = resultset.getInt(1);
				int user_id = resultset.getInt(2);
				UserService us_se = new UserServiceImpl();
				User user = us_se.findUserById(user_id);
				int lib_book_id = resultset.getInt(3);
				LibraryBookService lb_se = new LibraryBookServiceImpl();
				LibraryBook book = lb_se.findLibraryBookById(lib_book_id);
				String lending_date = resultset.getString(4);
				String return_date = resultset.getString(5);
				String returned_date = resultset.getString(6);
				int lending_status = resultset.getInt(7);
				
				lending = new Lending(id,user,book,lending_date,return_date,returned_date,lending_status);
				
				objectList.add(lending);
				
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
	public boolean deleteLending(int id) {
		// TODO Auto-generated method stub
		
		boolean is_delete = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("delete from lending where id=?;");
			statement.setInt(1, id);
			int check_id = statement.executeUpdate();
			is_delete = check_id>0;
			if(is_delete)
			{
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

	@Override
	public boolean updateLending1(Lending lending1) {
		// TODO Auto-generated method stub
		boolean is_update = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("update lending set returned_date=?,status=? where id=?");
			statement.setString(1, lending1.getReturned_date());
			statement.setInt(2, 0);
			statement.setInt(3, lending1.getId());
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

}
