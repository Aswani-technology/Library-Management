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
import com.bookcycle.service.LendingService;
import com.bookcycle.service.LibraryBookService;
import com.bookcycle.service.impl.LendingServiceImpl;
import com.bookcycle.service.impl.LibraryBookServiceImpl;

/**
 * Servlet implementation class LendingController
 */
@WebServlet("/LendingController")
public class LendingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LendingController() {
        super();
        // TODO Auto-generated constructor stub
        
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("id"));
		LendingService service = new LendingServiceImpl();
		String command = request.getParameter("command");
		switch(command)
		{
		
		case "findLending":
			
			Lending lending = service.findLendingById(id);
			request.setAttribute("lending", lending);
			System.out.println("lending is " + lending);
			request.getRequestDispatcher("webpages/librarian/lending/lending_update.jsp").forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Librarian logged_libr_list = (Librarian) session.getAttribute("logged_libr");
		LendingService service = new LendingServiceImpl();
		LibraryBookService libr_bk_service = new LibraryBookServiceImpl();
		String command = request.getParameter("command");
		switch(command)
		{
		case "add_lending":
			
			
			int bk_id = Integer.parseInt(request.getParameter("library_book"));
			LibraryBook librarybook = new LibraryBook();
			librarybook.setId(bk_id);
			libr_bk_service.updateLibraryBookStatus(librarybook, 0);
			Lending lending = readLending(request);
			service.saveLending(lending);
			List<Lending> lending_list = service.findAllLending();
			List<Lending> pending_list = lending_list.stream().filter(list->list.getLending_status()==1).collect(Collectors.toList());
			request.setAttribute("pending_list", pending_list);
			request.getRequestDispatcher("webpages/librarian/lending/lending_list.jsp").forward(request, response);
			
			break;
			
		case "update_lending":
			
			int logged_lib_id = logged_libr_list.getLibrary().getId();
			int bk_id1 = Integer.parseInt(request.getParameter("bk_id"));
			LibraryBook librarybook1 = new LibraryBook();
			librarybook1.setId(bk_id1);
			libr_bk_service.updateLibraryBookStatus(librarybook1, 1);
			Lending lending1 = readLending1(request);
			System.out.println(lending1);
			service.updateLending1(lending1);
			List<Lending> lending_list1 = service.findAllLending();
			List<Lending> records_list = lending_list1.stream().filter(list->list.getLending_status()==0 && list.getBook().getLibrary().getId()==logged_lib_id).collect(Collectors.toList());
			request.setAttribute("records_list", records_list);
			request.getRequestDispatcher("webpages/librarian/records/records.jsp").forward(request, response);
			break;
		}
	}

	private Lending readLending1(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		
		Lending lending1 = new Lending();
		int id = Integer.parseInt(request.getParameter("id"));
		lending1.setId(id);
		lending1.setReturned_date(request.getParameter("returned_date"));
		lending1.setLending_status(0);
		return lending1;
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
