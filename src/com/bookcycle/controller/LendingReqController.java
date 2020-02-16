package com.bookcycle.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookcycle.domain.Lending;
import com.bookcycle.domain.Librarian;
import com.bookcycle.domain.LibraryBook;
import com.bookcycle.domain.User;
import com.bookcycle.service.LendingReqService;
import com.bookcycle.service.LendingService;
import com.bookcycle.service.LibraryBookService;
import com.bookcycle.service.UserService;
import com.bookcycle.service.impl.LendingReqServiceImpl;
import com.bookcycle.service.impl.LendingServiceImpl;
import com.bookcycle.service.impl.LibraryBookServiceImpl;
import com.bookcycle.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LendingReqController
 */
@WebServlet("/LendingReqController")
public class LendingReqController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LendingReqController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int user_id = Integer.parseInt(request.getParameter("usr_id"));
		int book_id = Integer.parseInt(request.getParameter("bk_id"));
		UserService userservice = new UserServiceImpl();
		LibraryBookService bookservice = new LibraryBookServiceImpl();
		User user = userservice.findUserById(user_id);
		LibraryBook book = bookservice.findLibraryBookById(book_id);
		String command = request.getParameter("command");
		int id = Integer.parseInt(request.getParameter("id"));
		switch(command)
		{
		case "LendBook":
			
			request.setAttribute("id", id);
			request.setAttribute("user_det", user);
			request.setAttribute("book_det", book);
			request.getRequestDispatcher("webpages/librarian/lending/lending_add_req.jsp").forward(request, response);
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Librarian loggedlibrian = (Librarian) session.getAttribute("logged_libr");
		LendingService service = new LendingServiceImpl();
		LibraryBookService lib_bk_service = new LibraryBookServiceImpl();
		LendingReqService reqService = new LendingReqServiceImpl();
		String command = request.getParameter("command");
		switch(command)
		{
		case "add_lending_req":
			
			int bk_id = Integer.parseInt(request.getParameter("library_book"));
			LibraryBook libraryBook = new LibraryBook();
			libraryBook.setId(bk_id);
			lib_bk_service.updateLibraryBookStatus(libraryBook, 0);
			Lending lending = readLending(request);
			service.saveLending(lending);
			List<Lending> lending_list = service.findAllLending();
			List<Lending> pending_list = lending_list.stream().filter(list->list.getLending_status()==1).collect(Collectors.toList());
			request.setAttribute("pending_list", pending_list);
			request.getRequestDispatcher("webpages/librarian/lending/lending_list.jsp").forward(request, response);
			int req_id = Integer.parseInt(request.getParameter("id"));
			reqService.deleteLendingReq(req_id);
			break;
			
			
		}
	}

	private Lending readLending(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Lending lending = new Lending();
		User user = new User();
		user.setId(Integer.parseInt(request.getParameter("user")));
		lending.setUser(user);
		LibraryBook libraryBook = new LibraryBook();
		libraryBook.setId(Integer.parseInt(request.getParameter("library_book")));
		lending.setBook(libraryBook);
		lending.setLending_date(request.getParameter("lending_date"));
		lending.setReturn_date(request.getParameter("return_date"));
		lending.setReturned_date(request.getParameter("returned_date"));
		lending.setLending_status(1);
		
		return lending;
	}

}
