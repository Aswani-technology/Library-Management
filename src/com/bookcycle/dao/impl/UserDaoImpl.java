package com.bookcycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookcycle.dao.UserDao;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.User;
import com.bookcycle.util.Constants;

public class UserDaoImpl extends DatabaseDaoImpl implements UserDao{

	
	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		
		int key=0;
		System.out.println(user);
		try
		{
			connection = getConnection();
			System.out.println("======================user=======================");
			
			statement = connection.prepareStatement("insert into user(first_name,last_name,email,password,gender,address,dob,contact,pincode,district) values (?,?,?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFirst_name());
			statement.setString(2, user.getLast_name());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getGender());
			statement.setString(6, user.getAddress());
			statement.setString(7,user.getDob());
			statement.setString(8, user.getContact());
			statement.setInt(9, user.getPincode());
			statement.setString(10, user.getDistrict());
			
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
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		
		boolean is_Update = false;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("update user set "+ getAllColumnName() + " where id=?;");
			statement.setString(1, user.getFirst_name());
			statement.setString(2, user.getLast_name());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getGender());
			statement.setString(5, user.getAddress());
			statement.setString(6, user.getDob());
			statement.setString(7, user.getContact());
			statement.setInt(8, user.getPincode());
			statement.setString(9, user.getDistrict());
			statement.setInt(10, user.getId());
			
			int check_id=statement.executeUpdate();
			is_Update = check_id>0;
			if(is_Update)
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
		return is_Update;
	}

	private String getAllColumnName() {
		// TODO Auto-generated method stub
		
		StringBuilder builder = new StringBuilder();
		// .append(COLUMN_id).append("=?,").
		builder.append("first_name").append("=?,");
		builder.append("last_name").append("=?,");
		builder.append("email").append("=?,");
		builder.append("gender").append("=?,");
		builder.append("address").append("=?,");
		builder.append("dob").append("=?,");
		builder.append("contact").append("=?,");
		builder.append("pincode").append("=?,");
		builder.append("district").append("=?");
		return builder.toString();
	}
	
	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		
		User user = new User();
		
		ResultSet resultset = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from user where id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				String first_name=resultset.getString(2);
				String last_name = resultset.getString(3);
				String email = resultset.getString(4);
				String password = resultset.getString(5);
				String gender = resultset.getString(6);
				String address = resultset.getString(7);
				String dob = resultset.getString(8);
				String contact = resultset.getString(9);
				int pincode = resultset.getInt(10);
				String district = resultset.getString(11);
				int status = resultset.getInt(12);
				
				user = new User(id,first_name,last_name,email,password,gender,address,dob,contact,pincode,district,status);
				
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
		return user;
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		
		List<User> objectList = new ArrayList<User>();
		User user = new User();
		ResultSet resultset = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("select * from user;");
			resultset=statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				int id = resultset.getInt(1);
				String first_name=resultset.getString(2);
				String last_name = resultset.getString(3);
				String email = resultset.getString(4);
				String password = resultset.getString(5);
				String gender = resultset.getString(6);
				String address = resultset.getString(7);
				String dob = resultset.getString(8);
				String contact = resultset.getString(9);
				int pincode = resultset.getInt(10);
				String district = resultset.getString(11);
				int status = resultset.getInt(12);
				
				user = new User(id,first_name,last_name,email,password,gender,address,dob,contact,pincode,district,status);
				
				objectList.add(user);
				
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
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		
		boolean is_delete = false;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("delete from user where id=?;");
			statement.setInt(1,id);
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
	public User login(String email, String password) {
		// TODO Auto-generated method stub
		
		User user=new User();
		ResultSet resultset = null;
		connection = getConnection();
	
			try {
				statement = connection.prepareStatement("SELECT * FROM user  WHERE email=? AND password=?;");
				statement.setString(1, email);
				statement.setString(2, password);
				resultset = statement.executeQuery();
				while (resultset != null && resultset.next()) {
					int id = resultset.getInt(1);
					String first_name=resultset.getString(2);
					String last_name = resultset.getString(3);
					String email_id = resultset.getString(4);
					String pasword = resultset.getString(5);
					String gender = resultset.getString(6);
					String address = resultset.getString(7);
					String dob = resultset.getString(8);
					String contact = resultset.getString(9);
					int pincode = resultset.getInt(10);
					String district = resultset.getString(11);
					int status = resultset.getInt(12);
					
					user = new User(id,first_name,last_name,email,password,gender,address,dob,contact,pincode,district,status);
			} 
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
				
				
				
			
			closeDBResource();
			System.out.println("Find all data  Successfully");
		return user;
	}

}
