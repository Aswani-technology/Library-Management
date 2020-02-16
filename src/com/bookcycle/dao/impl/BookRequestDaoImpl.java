package com.bookcycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookcycle.dao.BookRequestDao;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.BookRequest;
import com.bookcycle.domain.User;
import com.bookcycle.service.UserService;
import com.bookcycle.service.impl.UserServiceImpl;
import com.bookcycle.util.Constants;

public class BookRequestDaoImpl extends DatabaseDaoImpl implements BookRequestDao {

	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int saveBookRequest(BookRequest bookRequest) {
		// TODO Auto-generated method stub
		
		int key = 0;
		System.out.println(bookRequest);
		try
		{
			connection = getConnection();
			System.out.println("======================user=======================");
			statement = connection.prepareStatement("insert into book_request(book_name,book_type,user_id) values (?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, bookRequest.getBook_name());
			statement.setString(2, bookRequest.getBook_type());
			statement.setInt(3, bookRequest.getUser().getId());
			statement.executeUpdate();
			ResultSet generatedKeys  = statement.getGeneratedKeys();
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
	public boolean updateBookRequest(BookRequest bookRequest) {
		// TODO Auto-generated method stub
		
		boolean is_update = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("update book_request set " + getAllColumnName() + "where id=?;");
			statement.setString(1, bookRequest.getBook_name());
			statement.setString(2, bookRequest.getBook_type());
			statement.setInt(3, bookRequest.getUser().getId());
			statement.setInt(4, bookRequest.getId());
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
		builder.append("book_name").append("=?,");
		builder.append("booktype").append("=?,");
		builder.append("user_id").append("=?");
		
		return builder.toString();
	}

	@Override
	public BookRequest findBookRequestById(int id) {
		// TODO Auto-generated method stub
		
		BookRequest bookRequest = new BookRequest();
		
		ResultSet resultset = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from book_request where id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			String book_name = resultset.getString(2);
			String book_type = resultset.getString(3);
			int user_id = resultset.getInt(4);
			UserService us_se = new UserServiceImpl();
			User us = us_se.findUserById(user_id);
			
			bookRequest = new BookRequest(id,book_name,book_type,us);
			Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
		}
		catch(SQLException e)
		{
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in finding data");
			e.printStackTrace();
		}
		closeDBResource();
		return bookRequest;
	
	}

	@Override
	public List<BookRequest> findAllBookRequest() {
		// TODO Auto-generated method stub
		
		List<BookRequest> objectList = new ArrayList<BookRequest>();
		BookRequest bookRequest = new BookRequest();
		ResultSet resultset = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from book_request;");
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				int id = resultset.getInt(1);
				String book_name = resultset.getString(2);
				String book_type = resultset.getString(3);
				int user_id = resultset.getInt(4);
				UserService us_se = new UserServiceImpl();
				User us = us_se.findUserById(user_id);
				
				bookRequest = new BookRequest(id,book_name,book_type,us);
				
				objectList.add(bookRequest);
				
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
	public boolean deleteBookRequest(int id) {
		// TODO Auto-generated method stub
		
		boolean is_delete = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("delete from book_request where id=?;");
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

}
