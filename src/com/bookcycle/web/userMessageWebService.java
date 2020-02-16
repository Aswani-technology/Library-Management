package com.bookcycle.web;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;







import com.bookcycle.domain.User;
import com.bookcycle.domain.userMessage;
import com.bookcycle.service.UserMessageService;
import com.bookcycle.service.impl.UserMessageServiceImpl;
import com.bookcycle.util.Constants;
import com.google.gson.Gson;

@Path("/userMessage")

public class userMessageWebService {
	
	UserMessageService service=new UserMessageServiceImpl();
	
	@POST
	@Path("/saveMessage")
	@Produces(MediaType.APPLICATION_JSON)
	public String saveMessage(userMessage message) throws JSONException{
		String response="0";
	
		int messageid=service.saveUserMessage(message);
		
		JSONObject jsonObject = new JSONObject();
		
		 if(messageid == 0)
		 {
			 response="0";
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		 
		 
		 
		 
		 response="1";
		 return Constants.Util.getResponseMessageForClient(jsonObject, response);
	
				
	}
	
	@GET
	@Path("/findAllBySender/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findMessage(@PathParam("id") int id) throws JSONException{
		String response="0";
		userMessage message=new userMessage();
		List<userMessage> message_list=service.findAllMessageBySenderId(id);
		JSONArray array = new JSONArray(new Gson().toJson(message_list));
		JSONObject jsonObject = new JSONObject();
		
		 if(message_list.isEmpty())
		 {
			 response="no Messages";
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		 JSONObject userjson = new JSONObject();
		 
		
		
		 userjson.put("message",array);
		 response="1";
		 return Constants.Util.getResponseMessageForClient(userjson, response);
	
				
	}
	@GET
	@Path("/findAllByReceiver/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findReceiverMessage(@PathParam("id") int id) throws JSONException{
		String response="0";
		userMessage message=new userMessage();
		List<userMessage> message_list=service.findAllMessageByReceiverId(id);
		JSONArray array = new JSONArray(new Gson().toJson(message_list));
		JSONObject jsonObject = new JSONObject();
		
		 if(message_list.isEmpty())
		 {
			 response="no Messages";
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		 JSONObject userjson = new JSONObject();
		 
		
		
		 userjson.put("message",array);
		 response="1";
		 return Constants.Util.getResponseMessageForClient(userjson, response);
	
				
	}
	

}
