package com.bookcycle.service;

import java.util.List;

import com.bookcycle.domain.userMessage;

public interface UserMessageService {

	public int saveUserMessage(userMessage message);
	public List<userMessage> findAllMessageBySenderId(int id);
	public List<userMessage> findAllMessageByReceiverId(int id);
}
