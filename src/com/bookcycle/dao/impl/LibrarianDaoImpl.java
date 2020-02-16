package com.bookcycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookcycle.dao.LibrarianDao;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.Librarian;
import com.bookcycle.domain.Library;
import com.bookcycle.service.LibraryService;
import com.bookcycle.service.impl.LibraryServiceImpl;
import com.bookcycle.util.Constants;

public class LibrarianDaoImpl extends DatabaseDaoImpl implements LibrarianDao {

	Connection connection = null;
	
	PreparedStatement statement = null;
	
	@Override
	public int saveLibrarian(Librarian librarian) {
		// TODO Auto-generated method stub
		int key = 0;
		System.out.println(librarian);
		try
		{
			connection = getConnection();
			System.out.println("======================user=======================");
			statement = connection.prepareStatement("insert into librarian(first_name,last_name,contact,email,password,address,pincode,district,status,library_id) values (?,?,?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, librarian.getFirst_name());
			statement.setString(2, librarian.getLast_name());
			statement.setLong(3, librarian.getContact());
			statement.setString(4, librarian.getEmail());
			statement.setString(5, librarian.getPassword());
			statement.setString(6, librarian.getAddress());
			statement.setInt(7, librarian.getPincode());
			statement.setString(8, librarian.getDistrict());
			statement.setInt(9, librarian.getStatus());
			statement.setInt(10, librarian.getLibrary().getId());
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
	public boolean updateLibrarian(Librarian librarian) {
		// TODO Auto-generated method stub
		boolean is_update = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("update librarian set "+ getAllColumnName()+ "where id=?");
			statement.setString(1, librarian.getFirst_name());
			statement.setString(2, librarian.getLast_name());
			statement.setLong(3, librarian.getContact());
			statement.setString(4, librarian.getEmail());
			statement.setString(5, librarian.getAddress());
			statement.setInt(6, librarian.getPincode());
			statement.setString(7, librarian.getDistrict());
			statement.setInt(8, librarian.getId());
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
		builder.append("first_name").append("=?,");
		builder.append("last_name").append("=?,");
		builder.append("contact").append("=?,");
		builder.append("email").append("=?,");
		builder.append("address").append("=?,");
		builder.append("pincode").append("=?,");
		builder.append("district").append("=?");
		return builder.toString();
	}

	@Override
	public Librarian findLibrarianById(int id) {
		// TODO Auto-generated method stub
		
		Librarian librarian = new Librarian();
		
		ResultSet resultset = null;
		
		try
		{
			System.out.println(id);
			connection = getConnection();
			statement = connection.prepareStatement("select * from librarian where id=?");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				String first_name = resultset.getString(2);
				String last_name = resultset.getString(3);
				Long contact = resultset.getLong(4);
				String email = resultset.getString(5);
				String address = resultset.getString(7);
				int pincode = resultset.getInt(8);
				String district = resultset.getString(9);
				
				librarian = new Librarian(id,first_name,last_name,contact,email,address,pincode,district);
				
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
		return librarian;
	}

	@Override
	public List<Librarian> findAllLibrarian() {
		// TODO Auto-generated method stub
		
		List<Librarian> objectList = new ArrayList<Librarian>();
		Librarian librarian = new Librarian();
		ResultSet resultset = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from librarian");
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				int id = resultset.getInt(1);
				String first_name = resultset.getString(2);
				String last_name = resultset.getString(3);
				Long contact = resultset.getLong(4);
				String email = resultset.getString(5);
				String password = resultset.getString(6);
				String address = resultset.getString(7);
				int pincode = resultset.getInt(8);
				String district = resultset.getString(9);
				int status = resultset.getInt(10);
				int lib_id = resultset.getInt(11);
				LibraryService li_se = new LibraryServiceImpl();
				Library lib = li_se.findLibraryById(lib_id);
				
				librarian = new Librarian(id,first_name,last_name,contact,email,password,address,pincode,district,status,lib);
				
				System.out.println("libraian details "+ librarian);
				
				objectList.add(librarian);
				
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
	public boolean deleteLibrarian(int id) {
		// TODO Auto-generated method stub
		
		boolean is_delete = false;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("delete from librarian where id=?;");
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
