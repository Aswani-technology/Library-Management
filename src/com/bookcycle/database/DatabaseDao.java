package com.bookcycle.database;

public interface DatabaseDao {

	final String DB_NAME="book_cycle";
	final String USERNAME="root";
	final String PASSWORD="qwerty";
	final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	public String getDriverName();
	public String getPassword();
	public String getURL();
	public String getUserName();
}
