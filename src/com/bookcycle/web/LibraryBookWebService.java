package com.bookcycle.web;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bookcycle.domain.BookRequest;
import com.bookcycle.domain.Library;
import com.bookcycle.domain.LibraryBook;
import com.bookcycle.domain.User;
import com.bookcycle.domain.UserBook;
import com.bookcycle.service.BookRequestService;
import com.bookcycle.service.LibraryBookService;
import com.bookcycle.service.LibraryService;
import com.bookcycle.service.impl.BookRequestServiceImpl;
import com.bookcycle.service.impl.LibraryBookServiceImpl;
import com.bookcycle.service.impl.LibraryServiceImpl;
import com.bookcycle.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

@Path("/libraryBook")

public class LibraryBookWebService {
	
	LibraryBookService service=new LibraryBookServiceImpl();
	
	/*@GET
	@Path("/searchLibraryBook/{book}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public String searchUserBook(User user,@PathParam("book") String book_Name) throws JSONException,NoSuchElementException{
		String response="0";000000000000000
		
		LibraryService libraryService=new LibraryServiceImpl();
		Library library=libraryService.findLibraryByUser(user);
		LibraryBook searchedBook=new LibraryBook();
		List<LibraryBook> libraryBook_list=service.findAllBookByLibraryId(library.getId());
		for(LibraryBook list:libraryBook_list){
			if(list.getBook_name().equals(book_Name)){
				searchedBook=list;
			}
		}
		
		System.out.println("Searched Book ******************** "+searchedBook);
		JSONObject jsonObject = new JSONObject();
		 JSONObject userjson = new JSONObject();
		 if(searchedBook.getId() == 0 )
		 {
			 response="0";
			 jsonObject.put("Book Not Found", 0);
			 return Constants.Util.getResponseMessageForClient(jsonObject, "0");
		 }
		 		
		 else if(searchedBook.getStatus()==1){
			 
			 userjson.put("Book unAvilable", searchedBook.getBook_name());
			 response="1";
			 return Constants.Util.getResponseMessageForClient(userjson, response);
			 
		 }
		
		 
		 else{
		
		 userjson.put("Book", new JSONObject(searchedBook));
		 response="1";
		 return Constants.Util.getResponseMessageForClient(userjson, response);
	
		} 
		 
	}
		*/
	
	
	@POST
	@Path("/findAllLibraryBook")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String findAllLibraryBook(User user) throws JsonProcessingException, JSONException{
		
		LibraryService libraryService=new LibraryServiceImpl();
		Library library=libraryService.findLibraryByUser(user);
		
		List<LibraryBook> libraryBook_list=service.findAllBookByLibraryId(library.getId());
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray(new Gson().toJson(libraryBook_list));
		String response = null;
		
		if(libraryBook_list.isEmpty()){
			response = "0";
			return Constants.Util.getResponseMessageForClient(jsonObject, response);
			
		}
		
		 response = "1";
		jsonObject.put("libraryBook_list",array);
		return Constants.Util.getResponseMessageForClient(jsonObject, response);
	
	}
	
	@POST
	@Path("/saveRequestBook")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addBook(BookRequest book) throws JSONException{

		BookRequestService bookService=new BookRequestServiceImpl();
		int id=bookService.saveBookRequest(book);
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
	@Path("/findLibraryBooks/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String findAllLibraryBook(@PathParam("id") int id) throws JsonProcessingException, JSONException{
		
		
		
		List<LibraryBook> libraryBook_list=service.findAllBookByLibraryId(id);
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray(new Gson().toJson(libraryBook_list));
		String response = null;
		
		if(libraryBook_list.isEmpty()){
			response = "0";
			return Constants.Util.getResponseMessageForClient(jsonObject, response);
			
		}
		
		 response = "1";
		jsonObject.put("libraryBook_list",array);
		return Constants.Util.getResponseMessageForClient(jsonObject, response);
	
	}

}
