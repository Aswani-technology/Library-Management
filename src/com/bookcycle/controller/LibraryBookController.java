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

import com.bookcycle.domain.BookType;
import com.bookcycle.domain.Librarian;
import com.bookcycle.domain.Library;
import com.bookcycle.domain.LibraryBook;
import com.bookcycle.service.LibraryBookService;
import com.bookcycle.service.impl.LibraryBookServiceImpl;

/**
 * Servlet implementation class LibraryBookController
 */
@WebServlet("/LibraryBookController")
public class LibraryBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Librarian logged_libr_list = (Librarian) session.getAttribute("logged_libr");
		
		int id = Integer.parseInt(request.getParameter("id"));
		LibraryBookService service = new LibraryBookServiceImpl();
		String command = request.getParameter("command");
		switch(command)
		{
		case "findLibraryBook":
			
			LibraryBook libraryBook = service.findLibraryBookById(id);
			request.setAttribute("librarybook", libraryBook);
			System.out.println("library book is " + libraryBook);
			request.getRequestDispatcher("webpages/admin/librarybook/librarybook_edit.jsp").forward(request, response);
			break;
			
		case "findLibraryBookDet":
			
			LibraryBook libraryBookDet = service.findLibraryBookById(id);
			request.setAttribute("librarybook", libraryBookDet);
			System.out.println("library book is " + libraryBookDet);
			request.getRequestDispatcher("webpages/admin/librarybook/librarybook_edit.jsp").forward(request, response);
			break;
			
		case "libr_findLibraryBook":
			
			LibraryBook libr_libraryBook = service.findLibraryBookById(id);
			request.setAttribute("librarybook", libr_libraryBook);
			System.out.println("library book is " + libr_libraryBook);
			request.getRequestDispatcher("webpages/librarian/librarybook/librarybook_edit.jsp").forward(request, response);
			break;
			
		case "deleteLibraryBook":
			
			service.deleteLibrary(id);
			List<LibraryBook> libraryBookList = service.findAllLibraryBook();
			request.setAttribute("librarybook_list", libraryBookList);
			request.getRequestDispatcher("webpages/admin/librarybook/librarybook_list.jsp").forward(request, response);
			break;
			
		case "libr_deleteLibraryBook":
			
			service.deleteLibrary(id);
			List<LibraryBook> libr_libraryBookList = service.findAllLibraryBook();
			request.setAttribute("librarybook_list", libr_libraryBookList);
			request.getRequestDispatcher("webpages/librarian/librarybook/librarybook_list.jsp").forward(request, response);
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
		int logged_library = logged_libr_list.getLibrary().getId();
		LibraryBookService service = new LibraryBookServiceImpl();
		String command = request.getParameter("command");
		switch(command)
		{
		case "add_librarybook":
			
			LibraryBook libraryBook = readLibraryBook(request);
			service.saveLibraryBook(libraryBook);
			List<LibraryBook> libraryBookList = service.findAllLibraryBook();
			for(LibraryBook list:libraryBookList)
			{
				System.out.println(list);
			}
			request.setAttribute("librarybook_list", libraryBookList);
			request.getRequestDispatcher("webpages/admin/librarybook/librarybook_list.jsp").forward(request, response);
			break;
			
		case "libr_add_librarybook":
			
			LibraryBook libr_libraryBook = readLibraryBook(request);
			service.saveLibraryBook(libr_libraryBook);
			List<LibraryBook> lib_libraryBookList = service.findAllLibraryBook();
			List<LibraryBook> library_books = lib_libraryBookList.stream().filter(list->list.getLibrary().getId()==logged_library&&list.getStatus()==1).collect(Collectors.toList());
			for(LibraryBook list:library_books)
			{
				System.out.println(list);
			}
			request.setAttribute("librarybook_list", library_books);
			request.getRequestDispatcher("webpages/librarian/librarybook/librarybook_list.jsp").forward(request, response);
			break;
			
		case "update_librarybook":
			
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			LibraryBook libraryBook2 = readLibraryBook1(request);
			libraryBook2.setId(id);
			service.updateLibraryBook(libraryBook2);
			List <LibraryBook> libraryBookList2 = service.findAllLibraryBook();
			request.setAttribute("librarybook_list", libraryBookList2);
			request.getRequestDispatcher("webpages/admin/librarybook/librarybook_list.jsp").forward(request, response);
			break;
			
		case "libr_update_librarybook":
			
			int lid = Integer.parseInt(request.getParameter("id"));
			System.out.println(lid);
			LibraryBook libr_libraryBook2 = readLibraryBook1(request);
			libr_libraryBook2.setId(lid);
			service.updateLibraryBook(libr_libraryBook2);
			List <LibraryBook> libr_libraryBookList2 = service.findAllLibraryBook();
			request.setAttribute("librarybook_list", libr_libraryBookList2);
			request.getRequestDispatcher("webpages/librarian/librarybook/librarybook_list.jsp").forward(request, response);
			break;
		}
	}

	private LibraryBook readLibraryBook1(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		LibraryBook libraryBook2 = new LibraryBook();
		libraryBook2.setBook_name(request.getParameter("book_name"));
		libraryBook2.setPublisher(request.getParameter("publisher"));
		libraryBook2.setAuthor(request.getParameter("author"));
		libraryBook2.setPrice(Double.parseDouble(request.getParameter("price")));
		
		return libraryBook2;
	}

	private LibraryBook readLibraryBook(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		LibraryBook libraryBook = new LibraryBook();
		libraryBook.setBook_name(request.getParameter("book_name"));
		libraryBook.setPublisher(request.getParameter("publisher"));
		libraryBook.setAuthor(request.getParameter("author"));
		int bktyp_id = Integer.parseInt(request.getParameter("booktype"));
		BookType bookType = new BookType();
		bookType.setId(bktyp_id);
		libraryBook.setBooktype(bookType);
		int lib_id = Integer.parseInt(request.getParameter("library"));
		Library library = new Library();
		library.setId(lib_id);
		libraryBook.setLibrary(library);
		libraryBook.setPrice(Double.parseDouble(request.getParameter("price")));
		libraryBook.setStatus(Integer.parseInt(request.getParameter("status")));
		
		return libraryBook;
	}

}
