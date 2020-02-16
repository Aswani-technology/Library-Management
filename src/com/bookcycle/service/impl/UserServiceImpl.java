package com.bookcycle.service.impl;

import java.util.List;

import com.bookcycle.dao.UserDao;
import com.bookcycle.dao.impl.UserDaoImpl;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.User;
import com.bookcycle.service.UserService;

public class UserServiceImpl extends DatabaseDaoImpl implements UserService{

	UserDao dao = new UserDaoImpl();
	
	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return dao.saveUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return dao.updateUser(user);
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return dao.findUserById(id);
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return dao.findAllUser();
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		return dao.deleteUser(id);
	}

	@Override
	public User login(String email, String password) {
		// TODO Auto-generated method stub
		return dao.login(email, password);
	}

}
