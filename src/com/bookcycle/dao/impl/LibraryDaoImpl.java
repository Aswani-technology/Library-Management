package com.bookcycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import com.bookcycle.dao.LibraryDao;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.Library;
import com.bookcycle.domain.User;
import com.bookcycle.util.Constants;

public class LibraryDaoImpl extends DatabaseDaoImpl implements LibraryDao {

	
	Connection connection = null;
	
	PreparedStatement statement = null;
	
	
	@Override
	public int saveLibrary(Library library) {
		// TODO Auto-generated method stub
		
		int key = 0;
		System.out.println(library);
		try
		{
			connection = getConnection();
			System.out.println("======================user=======================");
			statement = connection.prepareStatement("insert into library(name,address,pincode,district,phone) values(?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, library.getName());
			statement.setString(2, library.getAddress());
			statement.setString(3, library.getPincode());
			statement.setString(4, library.getDistrict());
			statement.setString(5, library.getPhone());
			
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
	public boolean updateLibrary(Library library) {
		// TODO Auto-generated method stub
		
		boolean is_update = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("update library set " +getAllColumnName() + " where id=?;");
			statement.setString(1, library.getName());
			statement.setString(2, library.getAddress());
			statement.setString(3, library.getPincode());
			statement.setString(4, library.getDistrict());
			statement.setString(5, library.getPhone());
			statement.setInt(6, library.getId());
			
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
		builder.append("name").append("=?,");
		builder.append("address").append("=?,");
		builder.append("pincode").append("=?,");
		builder.append("district").append("=?,");
		builder.append("phone").append("=?");
		
		return builder.toString();
	}

	@Override
	public Library findLibraryById(int id) {
		// TODO Auto-generated method stub
		
		Library library = new Library();
		
		ResultSet resultset = null;
		
		try
		{
			System.out.println(id);
			connection = getConnection();
			statement = connection.prepareStatement("select * from library where id =?");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				String name = resultset.getString(2);
				String address = resultset.getString(3);
				String pincode = resultset.getString(4);
				String district = resultset.getString(5);
				String phone = resultset.getString(6);
				
				
				library = new Library(id,name,address,pincode,district,phone);
				
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
		System.out.println(library);
		return library;
	}

	@Override
	public List<Library> findAllLibrary() {
		// TODO Auto-generated method stub
		
		List<Library> objectList = new ArrayList<Library>();
		Library library = new Library();
		ResultSet resultset = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from library;");
			resultset = statement.executeQuery();
			
			while(resultset!=null && resultset.next())
			{
				int id = resultset.getInt(1);
				String name = resultset.getString(2);
				String address = resultset.getString(3);
				String pincode = resultset.getString(4);
				String district = resultset.getString(5);
				String phone = resultset.getString(6);
				
				library = new Library(id,name,address,pincode,district,phone);
				
				objectList.add(library);
				
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
	public boolean deleteLibrary(int id) {
		// TODO Auto-generated method stub
		
		boolean is_delete = false;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("delete from library where id=?;");
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
	public Library findLibraryByUser(User user) {
		// TODO Auto-generated method stub
		Library library =new Library();
		int user_pin = user.getPincode();
		
		List<Library> library_list = findAllLibrary();
		
		for(Library list:library_list)
		{
			if(list.getPincode().equals(user_pin))
			{
				library = list;
			}
		}
		return library;
	}

	@Override
	public List<Library> findLibraryByPin(String dst) {
		// TODO Auto-generated method stub
		
		Library library = new Library();
		List<Library> library_list=new ArrayList<Library>();		
		ResultSet resultset = null;
		
		
		try
		{
			System.out.println(dst);
			connection = getConnection();
			statement = connection.prepareStatement("select * from library where district =?");
			statement.setString(1, dst);
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				int id=resultset.getInt(1);
				String name = resultset.getString(2);
				String address = resultset.getString(3);
				String pincode = resultset.getString(4);
				String district = resultset.getString(5);
				String phone = resultset.getString(6);
				
				
				library = new Library(id,name,address,pincode,district,phone);
				library_list.add(library);
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
		System.out.println(library_list);
		return library_list;
	}

}
