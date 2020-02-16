package com.bookcycle.dao;

import java.util.List;

import com.bookcycle.domain.User;

public interface UserDao {

	
	public int saveUser(User user);
	public boolean updateUser(User user);
	public User findUserById(int id);
	public List<User> findAllUser();
	public boolean deleteUser(int id);
	public User login(String email,String password);
	
	
}
