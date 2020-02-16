package com.bookcycle.service.impl;

import java.util.List;

import com.bookcycle.dao.UserMessageDao;
import com.bookcycle.dao.impl.UserMessageDaoImpl;
import com.bookcycle.database.DatabaseDaoImpl;
import com.bookcycle.domain.userMessage;
import com.bookcycle.service.UserMessageService;

public class UserMessageServiceImpl extends DatabaseDaoImpl implements UserMessageService{

	UserMessageDao dao = new UserMessageDaoImpl();
	
	@Override
	public int saveUserMessage(userMessage message) {
		// TODO Auto-generated method stub
		return dao.saveUserMessage(message);
	}

	@Override
	public List<userMessage> findAllMessageBySenderId(int id) {
		// TODO Auto-generated method stub
		
		return dao.findAllMessageBySenderId(id);
	}

	@Override
	public List<userMessage> findAllMessageByReceiverId(int id) {
		// TODO Auto-generated method stub
		return dao.findAllMessageByReceiverId(id);
	}

	
}
