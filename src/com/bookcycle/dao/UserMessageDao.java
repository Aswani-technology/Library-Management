package com.bookcycle.dao;

import java.util.List;

import com.bookcycle.domain.userMessage;

public interface UserMessageDao {

	public int saveUserMessage(userMessage message);
	
	public List<userMessage> findAllMessageBySenderId(int id);
	public List<userMessage> findAllMessageByReceiverId(int id);
}
