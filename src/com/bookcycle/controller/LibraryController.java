package com.bookcycle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookcycle.domain.Library;
import com.bookcycle.service.LibraryService;
import com.bookcycle.service.impl.LibraryServiceImpl;

/**
 * Servlet implementation class LibraryController
 */
@WebServlet("/LibraryController")
public class LibraryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("id"));
		LibraryService service = new LibraryServiceImpl();
		String command = request.getParameter("command");
		switch(command)
		{
		case "findLibrary":
			
			Library library = service.findLibraryById(id);
			request.setAttribute("library", library);
			System.out.println("library is " + library);
			request.getRequestDispatcher("webpages/admin/library/library_edit.jsp").forward(request, response);
			break;
			
		case "deleteLibrary":
			
			service.deleteLibrary(id);
			List<Library> libraryList = service.findAllLibrary();
			request.setAttribute("library_list", libraryList);
			request.getRequestDispatcher("webpages/admin/library/library_list.jsp").forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LibraryService service = new LibraryServiceImpl();
		String command = request.getParameter("command");
		switch(command)
		{
		case "add_library":
			
			Library library = readLibrary(request);
			service.saveLibrary(library);
			List<Library> libraryList = service.findAllLibrary();
			for(Library list:libraryList)
			{
				System.out.println(list);
			}
			request.setAttribute("library_list", libraryList);
			request.getRequestDispatcher("webpages/admin/library/library_list.jsp").forward(request, response);
			break;
			
		case "update_library":
			
			int id = Integer.parseInt(request.getParameter("id"));
			Library library2 = readLibrary(request);
			library2.setId(id);
			service.updateLibrary(library2);
			List<Library> libraryList2 = service.findAllLibrary();
			request.setAttribute("library_list", libraryList2);
			request.getRequestDispatcher("webpages/admin/library/library_list.jsp").forward(request, response);
			break;
			
			
			default:
				
				request.getRequestDispatcher("webpages/admin/pages-404-withoutmenus.html").forward(request, response);
		}
	}

	private Library readLibrary(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Library library = new Library();
		library.setName(request.getParameter("name"));
		library.setAddress(request.getParameter("address"));
		library.setPincode(request.getParameter("pincode"));
		library.setDistrict(request.getParameter("district"));
		library.setPhone(request.getParameter("phoneno"));
		
		
		return library;
	}
	
}

