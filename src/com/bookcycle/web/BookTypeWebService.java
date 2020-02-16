package com.bookcycle.web;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bookcycle.domain.BookType;
import com.bookcycle.service.BookTypeService;
import com.bookcycle.service.impl.BookTypeServiceImpl;
import com.bookcycle.util.Constants;
import com.google.gson.Gson;

@Path("/bookType")
public class BookTypeWebService {
	
	BookTypeService bktypservice = new BookTypeServiceImpl();
	
	@GET
	@Path("/find/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findBookType(@PathParam("id") int id) throws JSONException
	{
		
		BookType obj = bktypservice.findBookTypeById(id);
		String response = "0";
		
		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		if(obj.getId() == 0)
		{
			jsonObject.put("bookType", obj);
			return Constants.Util.getResponseMessageForClient(jsonObject, response);
		}
		
		JSONObject objjson = new JSONObject();
		
		objjson.put("bookType",new JSONObject(obj));
		response="1";
		return Constants.Util.getResponseMessageForClient(objjson, response);
		
	}

	
	@GET
	@Path("/findAll")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String findAllBookType() throws JSONException
	{
		
		List<BookType> list = bktypservice.findAllBooktype();
		JSONObject jsonObject = new JSONObject();
		
		JSONArray array = new JSONArray(new Gson().toJson(list));
		
		jsonObject.put("book_type_list", array);
		String response="1";
		return Constants.Util.getResponseMessageForClient(jsonObject, response);
		
	}
}
