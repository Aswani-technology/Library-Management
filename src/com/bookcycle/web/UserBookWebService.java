package com.bookcycle.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
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
import com.bookcycle.domain.UserBook;
import com.bookcycle.service.UserBookService;
import com.bookcycle.service.impl.UserBookServiceImpl;
import com.bookcycle.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;


@Path("/userBook")

public class UserBookWebService {
	
	UserBookService service=new UserBookServiceImpl();
	
	@POST
	@Path("/addBook")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addBook(UserBook userbook) throws JSONException{

		int id=service.saveUserBook(userbook);
		String response=null;
		JSONObject jsonObject = new JSONObject(); 
		
		if(id == 0)
		 {
			 response="0";
			 jsonObject.put("status", "0");
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 	
		JSONObject userjson = new JSONObject();
		 userjson.put("bookId", id);
		 response="1";
		 
		 
		 return Constants.Util.getResponseMessageForClient(userjson, response);
		
	}
	
	@GET
	@Path("/findUserBook/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findUserBook(@PathParam("id") int id) throws JSONException{
		String response="0";
		UserBook userBook=new UserBook();
		userBook=service.findUserBookById(id);
		JSONObject jsonObject = new JSONObject();
		
		 if(userBook.getId() == 0)
		 {
			 response="0";
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		 JSONObject userjson = new JSONObject();
		 
		
		
		 userjson.put("userBook", new JSONObject(userBook));
		 response="1";
		 return Constants.Util.getResponseMessageForClient(userjson, response);
	
				
	}
	
	@GET
	@Path("/findAllUserBook")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String findAllUserBook() throws JsonProcessingException, JSONException{
		
		
		
		List<UserBook> userBook_list=service.findAllUserBook();
		List<UserBook> book_list=userBook_list.stream().filter(list ->list.getStatus()==0)
				.collect(Collectors.toList());
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray(new Gson().toJson(book_list));
		String response = null;
		
		if(book_list.isEmpty()){
			response = "0";
			return Constants.Util.getResponseMessageForClient(jsonObject, response);
			
		}
		
		 response = "1";
		jsonObject.put("userBook_list",array);
		return Constants.Util.getResponseMessageForClient(jsonObject, response);
	
	}
	
	@GET
	@Path("/searchUserBook/{book}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchUserBook(@PathParam("book") String book_Name) throws JSONException{
		String response="0";
		UserBook userBook=new UserBook();
		userBook=service.findUserBookByName(book_Name);
		JSONObject jsonObject = new JSONObject();
		 JSONObject userjson = new JSONObject();
		 if(userBook.getId() == 0)
		 {
			 response="0";
			 jsonObject.put("Book Not Found", false);
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 		
		 else if(userBook.getStatus()==1){
			 
			 userjson.put("Book unAvilable", userBook.getBook_name());
			 response="1";
			 return Constants.Util.getResponseMessageForClient(userjson, response);
			 
		 }
		
		 
		 else{
		
		 userjson.put("userBook", new JSONObject(userBook));
		 response="1";
		 return Constants.Util.getResponseMessageForClient(userjson, response);
	
		 }
		
	}
	
	@GET
	@Path("/deleteUserBook/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUserBook(@PathParam("id") int id) throws JSONException{
		String response="0";
		
		boolean delete=service.deleteUserBook(id);
		JSONObject jsonObject = new JSONObject();
		
		 if(!delete)
		 {
			 response="0";
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		 JSONObject userjson = new JSONObject();
		 
		
		
		
		 response="1";
		 return Constants.Util.getResponseMessageForClient(userjson, response);
	
				
	}
	
	@POST
	@Path("/updateUserBook")
	@Produces(MediaType.APPLICATION_JSON)
	public String dupdateUserBook(UserBook userbook) throws JSONException{
		String response="0";
		
		boolean update=service.updateUserBook(userbook);
		JSONObject jsonObject = new JSONObject();
		
		 if(!update)
		 {
			 response="0";
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		 JSONObject userjson = new JSONObject();
		 
		
		
		
		 response="1";
		 return Constants.Util.getResponseMessageForClient(userjson, response);
	
				
	}
	@GET
	@Path("/findBookByUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findUserBookByUser(@PathParam("id") int id) throws JSONException{
		String response="0";
		List<UserBook> userBook=new ArrayList<UserBook>();
		userBook=service.findUserBookByUser(id);
		JSONObject jsonObject = new JSONObject();
		
		 if(userBook.isEmpty())
		 {
			 response="0";
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		 JSONObject userjson = new JSONObject();
		 
		
		
		 JSONArray array = new JSONArray(new Gson().toJson(userBook));
		 response="1";
		 jsonObject.put("userBooks",array);
		 return Constants.Util.getResponseMessageForClient(jsonObject, response);
	
				
	}
	
	

}
