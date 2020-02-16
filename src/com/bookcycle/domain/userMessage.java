package com.bookcycle.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor

@Getter
@Setter
@ToString
public class userMessage {

	int id;
	int senderId;
	int receiverId;
	User user;
	
	String message;
	String bookName;
	
	
	
	public userMessage(User user, String bookName,String message) {
		super();
		this.user = user;
		this.message = message;
	}
	
	



	public userMessage(int senderId, int receiverId, String message, String bookName) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.message = message;
		this.bookName = bookName;
	}
	
	
}
