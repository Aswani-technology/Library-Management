package com.bookcycle.web;

import java.util.ArrayList;
import java.util.List;

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

import com.bookcycle.domain.Library;
import com.bookcycle.domain.User;
import com.bookcycle.service.LibraryService;
import com.bookcycle.service.UserService;
import com.bookcycle.service.impl.LibraryServiceImpl;
import com.bookcycle.service.impl.UserServiceImpl;
import com.bookcycle.util.Constants;
import com.google.gson.Gson;


@Path("/user")

public class UserWebService {

	UserService userservice = new UserServiceImpl();
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String regUser(User user) throws JSONException
	{
		int id = userservice.saveUser(user);
		
		String response="0";
		
		JSONObject jsonObject = new JSONObject(); 
		
		if(id == 0)
		 {
			 response="0";
			 jsonObject.put("status", "0");
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		 
		 response="1";
		 return Constants.Util.getResponseMessageForClient(jsonObject, response);
	}
	
	
	@GET
	@Path("/findUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findUser(@PathParam("id") int id) throws JSONException{
		String response="0";
		User user=new User();
		user=userservice.findUserById(id);
		JSONObject jsonObject = new JSONObject();
		
		 if(user.getId() == 0)
		 {
			 response="0";
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		 JSONObject userjson = new JSONObject();
		 
		
		
		 userjson.put("user", new JSONObject(user));
		 response="1";
		 return Constants.Util.getResponseMessageForClient(userjson, response);
	
				
	}
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String login(User user) throws JSONException{
		
		UserService service=new UserServiceImpl();
		String response="0";
		User obj=service.login(user.getEmail(), user.getPassword());
		JSONObject jsonObject = new JSONObject();
		
		 if(obj.getId() == 0)
		 {
			 
			 //jsonObject.put("user", new JSONObject(obj));
			 response = "0";
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
			
		 }
		 				 		 
		 JSONObject main = new JSONObject();
		main.put("user", new JSONObject(obj));
		response = "1";
		 return Constants.Util.getResponseMessageForClient(main, response);
		
	
		
	}
	
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(User user) throws JSONException
	{
		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		String response="0";
		 
		boolean result=userservice.updateUser(user);
		System.out.println("result "+result);
		if(!result) {
			jsonObject.put("status", "0");
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
	    }
		 response="1";
		 return Constants.Util.getResponseMessageForClient(jsonObject, response);
	}
	
	@GET
	@Path("/findAll")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findAllUsers() throws JSONException
	{
		
		

		String response=null;
		 
		List<User> list=userservice.findAllUser();
		JSONObject jsonObject = new JSONObject();
		
		 
		
		
		JSONArray array = new JSONArray(new Gson().toJson(list));
		if(list.isEmpty()){
			response="0";
			return Constants.Util.getResponseMessageForClient(jsonObject, response);
			
		}
	 
		response="1";
		jsonObject.put("users", array);
		
		return Constants.Util.getResponseMessageForClient(jsonObject, response);
	}
	
	@GET
	@Path("/findUserLibrary/{dst}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findUserLibrary(@PathParam("dst")String dsct) throws JSONException{
		String response="0";
		System.out.println("Library"+dsct);
		User user=new User();
		List<Library> library=new ArrayList<Library>();
		LibraryService library_service=new LibraryServiceImpl();
		library=library_service.findLibraryByPin(dsct);
	
		JSONObject jsonObject = new JSONObject();
		
		 if(library.isEmpty())
		 {
			 response="0";
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 				 		 
		 JSONObject libraryjson = new JSONObject();
		 
		 JSONArray array = new JSONArray(new Gson().toJson(library));
		
		 libraryjson.put("library", array);
		 response="1";
		 return Constants.Util.getResponseMessageForClient(libraryjson, response);
	
				
	}
	
	
	

}
