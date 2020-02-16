package com.bookcycle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookcycle.domain.Librarian;
import com.bookcycle.domain.Library;
import com.bookcycle.service.LibrarianService;
import com.bookcycle.service.impl.LibrarianServiceImpl;

/**
 * Servlet implementation class LibrarianController
 */
@WebServlet("/LibrarianController")
public class LibrarianController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("id"));
		LibrarianService service = new LibrarianServiceImpl();
		String command = request.getParameter("command");
		switch(command)
		{
		case "findLibrarian":
			Librarian librarian = service.findLibrarianById(id);
			request.setAttribute("librarian", librarian);
			System.out.println("librarian is " + librarian );
			request.getRequestDispatcher("webpages/admin/librarian/librarian_edit.jsp").forward(request, response);
			break;
			
		case "deleteLibrarian":
			
			service.deleteLibrarian(id);
			List <Librarian> librarianList = service.findAllLibrarian();
			request.setAttribute("librarian_list", librarianList);
			request.getRequestDispatcher("webpages/admin/librarian/librarian_list.jsp").forward(request, response);
			break;
			
		}
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		LibrarianService service  = new LibrarianServiceImpl();
		String command = request.getParameter("command");
		switch(command)
		{
		case "add_librarian":
			
			Librarian librarian = readLibrarian(request);
			service.saveLibrarian(librarian);
			List<Librarian> librarianList = service.findAllLibrarian();
			for(Librarian list:librarianList)
			{
				System.out.println(list);
			}
			request.setAttribute("librarian_list", librarianList);
			request.getRequestDispatcher("webpages/admin/librarian/librarian_list.jsp").forward(request, response);
			break;
		
		case "update_librarian":
			
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			Librarian librarian2 = readLibrarian1(request);
			librarian2.setId(id);
			service.updateLibrarian(librarian2);
			List<Librarian> librarianList2 = service.findAllLibrarian();
			for(Librarian list1:librarianList2)
			{
				System.out.println(list1);
			}
			request.setAttribute("librarian_list", librarianList2);
			request.getRequestDispatcher("webpages/admin/librarian/librarian_list.jsp").forward(request, response);
			break;
					
		default:
				
			request.getRequestDispatcher("webpages/admin/pages-404-withoutmenus.html").forward(request, response);
		}
	}


	private Librarian readLibrarian1(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Librarian librarian1 = new Librarian();
		librarian1.setFirst_name(request.getParameter("f_name"));
		librarian1.setLast_name(request.getParameter("l_name"));
		librarian1.setContact(Long.parseLong(request.getParameter("contact")));
		librarian1.setEmail(request.getParameter("email"));
		librarian1.setAddress(request.getParameter("address"));
		librarian1.setPincode(Integer.parseInt(request.getParameter("pincode")));
		librarian1.setDistrict(request.getParameter("district"));
		
		
		return librarian1;
	}

	private Librarian readLibrarian(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Librarian librarian = new Librarian();
		librarian.setFirst_name(request.getParameter("f_name"));
		librarian.setLast_name(request.getParameter("l_name"));
		librarian.setContact(Long.parseLong(request.getParameter("contact")));
		librarian.setEmail(request.getParameter("email"));
		librarian.setPassword(request.getParameter("password"));
		librarian.setAddress(request.getParameter("address"));
		librarian.setPincode(Integer.parseInt(request.getParameter("pincode")));
		librarian.setDistrict(request.getParameter("district"));
		librarian.setStatus(Integer.parseInt(request.getParameter("status")));
		int lib_id = Integer.parseInt(request.getParameter("library"));
		Library library = new Library();
		library.setId(lib_id);
		librarian.setLibrary(library);
		
		
		return librarian;
	}

}
