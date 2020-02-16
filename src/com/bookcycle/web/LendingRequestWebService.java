package com.bookcycle.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import com.bookcycle.domain.BookRequest;
import com.bookcycle.domain.LendingReq;
import com.bookcycle.service.BookRequestService;
import com.bookcycle.service.LendingReqService;
import com.bookcycle.service.impl.BookRequestServiceImpl;
import com.bookcycle.service.impl.LendingReqServiceImpl;
import com.bookcycle.util.Constants;

@Path("/lendingBook")
public class LendingRequestWebService {
	@POST
	@Path("/saveLendingRequest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addBook(LendingReq req) throws JSONException{

		LendingReqService service=new LendingReqServiceImpl();
		int id=service.saveLendingReq(req);
		String response=null;
		JSONObject jsonObject = new JSONObject(); 
		
		if(id == 0)
		 {
			 response="0";
			 jsonObject.put("status", "0");
			 return Constants.Util.getResponseMessageForClient(jsonObject, response);
		 }
		 	
		JSONObject userjson = new JSONObject();
		 userjson.put("requestId", id);
		 response="1";
		 
		 
		 return Constants.Util.getResponseMessageForClient(userjson, response);
		
	}

}
