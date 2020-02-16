package com.bookcycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookcycle.dao.BookTypeDao;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.BookType;
import com.bookcycle.util.Constants;

public class BookTypeDaoImpl extends DatabaseDaoImpl implements BookTypeDao
{

	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int saveBookType(BookType bookType) {
		// TODO Auto-generated method stub
		
		int key=0;
		System.out.println(bookType);
		try
		{
			connection = getConnection();
			System.out.println("======================user=======================");
			
			statement = connection.prepareStatement("insert into book_type(name) values(?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, bookType.getName());
			
			statement.executeUpdate();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if(generatedKeys.next())
			{
				System.out.println("Insertion Done Successfully...");
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
	public boolean updateBookType(BookType bookType) {
		// TODO Auto-generated method stub
		
		boolean is_update=false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("update book_type set " + getAllColumnName() + "where id=?;");
			
			statement.setString(1, bookType.getName());
			statement.setInt(2, bookType.getId());
			
			int check_id = statement.executeUpdate();
			is_update = (check_id > 0);
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
		builder.append("name").append("=?");
		return builder.toString();
	}

	@Override
	public BookType findBookTypeById(int id) {
		// TODO Auto-generated method stub
		
		BookType bookType = new BookType();
		
		ResultSet resultset = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from book_type where id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				String name = resultset.getString(2);
				
				bookType = new BookType(id,name);
				
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
		System.out.println("Find all data  Successfully");
		return bookType;
	}

	@Override
	public List<BookType> findAllBookType() {
		// TODO Auto-generated method stub
		
		List <BookType> objectList = new ArrayList<BookType>();
		BookType bookType = new BookType();
		ResultSet resultset = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from book_type;");
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				int id = resultset.getInt(1);
				String name = resultset.getString(2);
				
				bookType= new BookType(id,name);
				
				objectList.add(bookType);
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
	public boolean deleteBookType(int id) {
		// TODO Auto-generated method stub
		
		boolean is_Delete = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("delete from book_type where id=?;");
			statement.setInt(1, id);
			int check_id = statement.executeUpdate();
			is_Delete = check_id>0;
			if(is_Delete)
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
		return is_Delete;
	}
	
}
