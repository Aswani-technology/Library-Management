package com.bookcycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookcycle.dao.UserBookDao;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.BookType;
import com.bookcycle.domain.User;
import com.bookcycle.domain.UserBook;
import com.bookcycle.service.BookTypeService;
import com.bookcycle.service.UserService;
import com.bookcycle.service.impl.BookTypeServiceImpl;
import com.bookcycle.service.impl.UserServiceImpl;
import com.bookcycle.util.Constants;


public class UserBookDaoImpl extends DatabaseDaoImpl implements UserBookDao{

	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int saveUserBook(UserBook userbook) {
		// TODO Auto-generated method stub
		
		int key = 0;
		System.out.println(userbook);
		try
		{
			connection = getConnection();
			System.out.println("======================user=======================");
			statement = connection.prepareStatement("insert into userbook(book_name,publisher,author,booktype_id,user_id,status) values (?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, userbook.getBook_name());
			statement.setString(2, userbook.getPublisher());
			statement.setString(3, userbook.getAuthor());
			statement.setInt(4, userbook.getBooktype().getId());
			statement.setInt(5, userbook.getUser().getId());
			statement.setInt(6, userbook.getStatus());
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
	public boolean updateUserBook(UserBook userbook) {
		// TODO Auto-generated method stub
		System.out.println("update"+userbook);
		boolean is_update = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("update userbook set "+ getAllColumnName()+ " where id=?;");
			statement.setString(1, userbook.getBook_name());
			statement.setString(2, userbook.getPublisher());
			statement.setString(3, userbook.getAuthor());
			statement.setInt(4, userbook.getBooktype().getId());
			statement.setInt(5, userbook.getUser().getId());
			statement.setInt(6, userbook.getStatus());
			statement.setInt(7, userbook.getId());
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
		builder.append("book_name").append("=?,");
		builder.append("publisher").append("=?,");
		builder.append("author").append("=?,");
		builder.append("booktype_id").append("=?,");
		builder.append("user_id").append("=?,");
		builder.append("status").append("=?");
		return builder.toString();
	}

	@Override
	public UserBook findUserBookById(int id) {
		// TODO Auto-generated method stub
		
		UserBook userbook = new UserBook();
		
		ResultSet resultset = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from userbook where id=?;");
			statement.setInt(1,id);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				String book_name = resultset.getString(2);
				String publisher = resultset.getString(3);
				String author = resultset.getString(4);
				int booktype_id = resultset.getInt(5);
				BookTypeService bt_se = new BookTypeServiceImpl();
				BookType bt = bt_se.findBookTypeById(booktype_id);
				int user_id = resultset.getInt(6);
				UserService us_se = new UserServiceImpl();
				User us = us_se.findUserById(user_id);
				int status = resultset.getInt(7);
				
				userbook = new UserBook(id,book_name,publisher,author,bt.getName(),us,status);
				
				
			}
			
			Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
		}
		catch(SQLException e)
		{
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in finding data");
			e.printStackTrace();
		}
		closeDBResource();
		return userbook;
	}

	@Override
	public List<UserBook> findAllUserBook() {
		// TODO Auto-generated method stub
		
		List<UserBook> objectList = new ArrayList<UserBook>();
		UserBook userbook = new UserBook();
		ResultSet resultset = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from userbook;");
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				int id = resultset.getInt(1);
				String book_name = resultset.getString(2);
				String publisher = resultset.getString(3);
				String author = resultset.getString(4);
				int booktype_id = resultset.getInt(5);
				BookTypeService bt_se = new BookTypeServiceImpl();
				BookType bt = bt_se.findBookTypeById(booktype_id);
				int user_id = resultset.getInt(6);
				UserService us_se = new UserServiceImpl();
				User us = us_se.findUserById(user_id);
				int status = resultset.getInt(7);
				
				userbook = new UserBook(id,book_name,publisher,author,bt.getName(),us,status);
				
				objectList.add(userbook);
				
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
	public boolean deleteUserBook(int id) {
		// TODO Auto-generated method stub
		
		boolean is_delete = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("delete from userbook where id=?;");
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
	public UserBook findUserBookByName(String name) {
		// TODO Auto-generated method stub
		
UserBook userbook = new UserBook();
		
		ResultSet resultset = null;
		connection = getConnection();
		try {
			statement = connection.prepareStatement("select * from userbook where book_name=?;");
			statement.setString(1, name);
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				
			int id=resultset.getInt(1);
			String book_name = resultset.getString(2);
			String publisher = resultset.getString(3);
			String author = resultset.getString(4);
			int booktype_id = resultset.getInt(5);
			BookTypeService bt_se = new BookTypeServiceImpl();
			BookType bt = bt_se.findBookTypeById(booktype_id);
			int user_id = resultset.getInt(6);
			UserService us_se = new UserServiceImpl();
			User us = us_se.findUserById(user_id);
			int status = resultset.getInt(7);
			
			userbook = new UserBook(id,book_name,publisher,author,bt.getName(),us,status);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDBResource();
		return userbook;
	}

	@Override
	public List<UserBook> findUserBookByUser(int userid) {
		// TODO Auto-generated method stub
UserBook userbook = new UserBook();
		System.out.println("user id"+userid);
		ResultSet resultset = null;
		List<UserBook> bookList=new ArrayList<UserBook>();
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from userbook where user_id =?;");
			statement.setInt(1,userid);
			
			resultset = statement.executeQuery();
			while (resultset != null && resultset.next()) {
				
				int id=resultset.getInt(1);
				String book_name = resultset.getString(2);
				String publisher = resultset.getString(3);
				String author = resultset.getString(4);
				int booktype_id = resultset.getInt(5);
				BookTypeService bt_se = new BookTypeServiceImpl();
				BookType bt = bt_se.findBookTypeById(booktype_id);
				//int user_id = resultset.getInt(6);
				UserService us_se = new UserServiceImpl();
				User us = us_se.findUserById(userid);
				int status = resultset.getInt(7);
				
				userbook = new UserBook(id,book_name,publisher,author,bt.getName(),us,status);
				bookList.add(userbook);
			}
			
			
			Constants.Response.MSG = Constants.Response.MSG_SUCCESS;
		}
		catch(SQLException e)
		{
			Constants.Response.MSG = Constants.Response.MSG_FAILED;
			System.out.println("Error occured in finding data");
			e.printStackTrace();
		}
		closeDBResource();
		return bookList;
	}

}
