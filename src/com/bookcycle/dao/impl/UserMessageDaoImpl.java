package com.bookcycle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookcycle.dao.UserMessageDao;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.User;
import com.bookcycle.domain.userMessage;
import com.bookcycle.service.impl.UserServiceImpl;

public class UserMessageDaoImpl extends DatabaseDaoImpl implements UserMessageDao{

	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int saveUserMessage(userMessage message) {
		// TODO Auto-generated method stub
		
		int key=0;
		System.out.println(message);
		try
		{
			connection = getConnection();
			System.out.println("======================userMessage=======================");
			
			statement = connection.prepareStatement("insert into message(sender_id,receiver_id,message,bookName) values (?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, message.getSenderId());
			statement.setInt(2, message.getReceiverId());
			statement.setString(3,message.getMessage());
			statement.setString(4,message.getBookName());
			statement.executeUpdate();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if(generatedKeys.next())
			{
				System.out.println("Insertion done successfully...");
			
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
	public List<userMessage> findAllMessageBySenderId(int id) {
		// TODO Auto-generated method stub
		userMessage message = new userMessage();
		ResultSet resultset = null;
		connection = getConnection();
		List<userMessage> message_list=new ArrayList<userMessage>();
		try {
			statement = connection.prepareStatement("select receiver_id,bookName,message from message where sender_id =?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				int receiver_id=resultset.getInt(1);
				User receiver=new UserServiceImpl().findUserById(receiver_id);
				String bookName=resultset.getString(2);
				String messages = resultset.getString(3);
				message=new userMessage(receiver,bookName,messages);
				message_list.add(message);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return message_list;
	}

	@Override
	public List<userMessage> findAllMessageByReceiverId(int id) {
		// TODO Auto-generated method stub
		userMessage message = new userMessage();
		ResultSet resultset = null;
		connection = getConnection();
		List<userMessage> message_list=new ArrayList<userMessage>();
		try {
			statement = connection.prepareStatement("select sender_id,bookName,message from message where receiver_id=?;");
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			while(resultset!=null && resultset.next())
			{
				int sender_id=resultset.getInt(1);
				User sender=new UserServiceImpl().findUserById(sender_id);
				String bookName=resultset.getString(2);
				String messages = resultset.getString(3);
				message=new userMessage(sender,bookName,messages);
				message_list.add(message);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return message_list;
	}

}
