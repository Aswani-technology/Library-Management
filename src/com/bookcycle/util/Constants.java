package com.bookcycle.util;

import org.json.JSONException;
import org.json.JSONObject;


public class Constants {
	public static class Response {
		public static final String SUCCESS = "success";
		public static final String FAILED = "failed";
	    public static String MSG ="Error";
	    public static String MSG_DATA_ALREADY_EXISTS ="This data already exist in this collection";
		public static  String MSG_ACCOUNT_CREATION_ALREADY_EXIST = "Authentication problem :, This Email address is already verified for another User Account";
		public static  String MSG_ACCOUNT_CREATION_SERVER_ERROR="Server Error :,Unable To Create This Account Try Again Later";
		public static  String MSG_SERVER_ERROR="Server Error";
		public static  String MSG_SUCCESS = "Successfully Completed Your Request";
		public static  String MSG_FAILED = "Request processing failed";
		public static String MSG_INVALID_KEY_AND_PASS="Login Failed,Invalid Key Or Password";
		public static String MSG_LOGIN_SUCCESS="Login Success";
		}
	
	public static class Util{
 		
		public static String getResponseMessageForClient(JSONObject json,String status){
			try {
			json.put("status", status);
			
			if(status.equals("0"))
			{
			json.put("server_message",Constants.Response.MSG);
			}
			else
			{
				json.put("server_message", Constants.Response.SUCCESS);
			}
			
			json.put("server_message", Constants.Response.MSG_SUCCESS);
				
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(json.toString());
		return json.toString();
	}
		
	}
}
