package com.bookcycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookcycle.dao.LibraryBookDao;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.BookType;
import com.bookcycle.domain.Library;
import com.bookcycle.domain.LibraryBook;
import com.bookcycle.service.BookTypeService;
import com.bookcycle.service.LibraryService;
import com.bookcycle.service.impl.BookTypeServiceImpl;
import com.bookcycle.service.impl.LibraryServiceImpl;
import com.bookcycle.util.Constants;

public class LibraryBookDaoImpl extends DatabaseDaoImpl implements LibraryBookDao{

	
	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int saveLibraryBook(LibraryBook librarybook) {
		// TODO Auto-generated method stub
		
		int key = 0;
		System.out.println(librarybook);
		try
		{
			connection = getConnection();
			System.out.println("======================user=======================");
			statement = connection.prepareStatement("insert into library_book(book_name,publisher,author,booktype_id,library_id,price,status) values(?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, librarybook.getBook_name());
			statement.setString(2, librarybook.getPublisher());
			statement.setString(3, librarybook.getAuthor());
			statement.setInt(4, librarybook.getBooktype().getId());
			statement.setInt(5, librarybook.getLibrary().getId());
			statement.setDouble(6,librarybook.getPrice());
			statement.setInt(7, librarybook.getStatus());
			statement.execute();
			
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
	public boolean updateLibraryBook(LibraryBook librarybook) {
		// TODO Auto-generated method stub
		
		boolean is_update = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("update library_book set "+ getAllColumnName()+ "where id=?");
			statement.setString(1,librarybook.getBook_name());
			statement.setString(2, librarybook.getPublisher());
			statement.setString(3, librarybook.getAuthor());
			statement.setDouble(4, librarybook.getPrice());
			statement.setInt(5, librarybook.getId());
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
		builder.append("price").append("=?");
		
		return builder.toString();
	}

	@Override
	public LibraryBook findLibraryBookById(int id) {
		// TODO Auto-generated method stub
		
		LibraryBook librarybook = new LibraryBook();
		
		ResultSet resultset = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from library_book where id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			
			while(resultset!=null && resultset.next())
			{
				String book_name = resultset.getString(2);
				String publisher = resultset.getString(3);
				String author = resultset.getString(4);
				int booktype_id = resultset.getInt(5);
				BookTypeService bt_se = new BookTypeServiceImpl();
				BookType bt = bt_se.findBookTypeById(booktype_id);
				int lib_id = resultset.getInt(6);
				LibraryService li_se = new LibraryServiceImpl();
				Library li = li_se.findLibraryById(lib_id);
				Double price = resultset.getDouble(7);
				int status = resultset.getInt(8);
				
				librarybook = new LibraryBook(id,book_name,publisher,author,bt,li,price,status);
				
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
		return librarybook;
	}

	@Override
	public List<LibraryBook> findAllLibraryBook() {
		// TODO Auto-generated method stub
		
		List<LibraryBook> objectList = new ArrayList<LibraryBook>();
		LibraryBook librarybook = new LibraryBook();
		ResultSet resultset = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from library_book;");
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
				int lib_id = resultset.getInt(6);
				LibraryService li_se = new LibraryServiceImpl();
				Library li = li_se.findLibraryById(lib_id);
				Double price = resultset.getDouble(7);
				int status = resultset.getInt(8);
				
				librarybook = new LibraryBook(id,book_name,publisher,author,li,price,status,bt.getName());
				
				objectList.add(librarybook);
				
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
	public boolean deleteLibraryBook(int id) {
		// TODO Auto-generated method stub
		boolean is_delete = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("delete from library_book where id=?;");
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
	public boolean updateLibraryBookStatus(LibraryBook librarybook, int status) {
		// TODO Auto-generated method stub
		boolean is_update = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("update library_book set status=? where id=?");
			statement.setInt(1, status);
			statement.setInt(2, librarybook.getId());
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
	public List<LibraryBook> findAllBookByLibraryId(int lid) {
		// TODO Auto-generated method stub
		
		List<LibraryBook> objectList = new ArrayList<LibraryBook>();
		LibraryBook librarybook = new LibraryBook();
		ResultSet resultset = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from library_book where library_id=?;");
			statement.setInt(1,lid);
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
				int lib_id = resultset.getInt(6);
				LibraryService li_se = new LibraryServiceImpl();
				Library li = li_se.findLibraryById(lib_id);
				Double price = resultset.getDouble(7);
				int status = resultset.getInt(8);
				
				librarybook = new LibraryBook(id,book_name,publisher,author,li,price,status,bt.getName());
				
				objectList.add(librarybook);
				
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
		return(objectList);
	}


}
