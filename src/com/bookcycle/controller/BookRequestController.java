package com.bookcycle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookcycle.domain.BookRequest;
import com.bookcycle.domain.BookType;
import com.bookcycle.domain.User;
import com.bookcycle.service.BookRequestService;
import com.bookcycle.service.BookTypeService;
import com.bookcycle.service.UserService;
import com.bookcycle.service.impl.BookRequestServiceImpl;
import com.bookcycle.service.impl.BookTypeServiceImpl;
import com.bookcycle.service.impl.UserServiceImpl;

/**
 * Servlet implementation class BookRequestController
 */
@WebServlet("/BookRequestController")
public class BookRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookRequestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("id"));
		BookRequestService service = new BookRequestServiceImpl();
		UserService usr_service = new UserServiceImpl();
		String command = request.getParameter("command");
		switch(command)
		{
		
		case "messageUser":
			
			BookRequest req = new BookRequest();
			req  = service.findBookRequestById(id);
			int user_id = req.getUser().getId();
			User user  = usr_service.findUserById(user_id);
			request.setAttribute("requester", user);
			request.getRequestDispatcher("webpages/admin/msg/addmsg.jsp").forward(request, response);
			break;
			
		case "deleteBookRequest":
			
			service.deleteBookRequest(id);
			List <BookRequest> bookRequestList = service.findAllBookRequest();
			request.setAttribute("bookrequest_list",bookRequestList );
			request.getRequestDispatcher("webpages/admin/bookrequest/bookrequest_list.jsp").forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
