package com.bookcycle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookcycle.domain.BookType;
import com.bookcycle.service.BookTypeService;
import com.bookcycle.service.impl.BookTypeServiceImpl;

/**
 * Servlet implementation class BookTypeController
 */
@WebServlet("/BookTypeController")
public class BookTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookTypeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int id = Integer.parseInt(request.getParameter("id"));
		BookTypeService service = new BookTypeServiceImpl();
		String command = request.getParameter("command");
		switch(command)
		{
		case "findBookType":
			
			BookType bookType = service.findBookTypeById(id);
			request.setAttribute("book_type", bookType);
			System.out.println("book type is "+ bookType);
			request.getRequestDispatcher("webpages/admin/booktype/booktype_edit.jsp").forward(request, response);
			break;
			
		case "libr_findBookType":
			
			BookType libr_bookType = service.findBookTypeById(id);
			request.setAttribute("book_type", libr_bookType);
			System.out.println("book type is "+ libr_bookType);
			request.getRequestDispatcher("webpages/librarian/booktype/booktype_edit.jsp").forward(request, response);
			break;
			
		case "deleteBookType":
			
			service.deleteBookType(id);
			List <BookType> bookTypeList = service.findAllBooktype();
			request.setAttribute("book_type_list", bookTypeList);
			request.getRequestDispatcher("webpages/admin/booktype/booktype_list.jsp").forward(request, response);
			break;
			
		case "libr_deleteBookType":
			
			service.deleteBookType(id);
			List <BookType> libr_bookTypeList = service.findAllBooktype();
			request.setAttribute("book_type_list", libr_bookTypeList);
			request.getRequestDispatcher("webpages/librarian/booktype/booktype_list.jsp").forward(request, response);
			break;
			
			
			default: 
				
				request.getRequestDispatcher("webpages/pages-404-withoutmenus.html").forward(request, response);
				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BookTypeService service = new BookTypeServiceImpl();
		String command = request.getParameter("command");
		switch(command)
		{
		case "add_book_type":
			
			BookType booktype = readBookType(request);
			service.saveBookType(booktype);
			
			List<BookType> bookTypeList = service.findAllBooktype();
			for(BookType list:bookTypeList)
			{
				System.out.println(list.getName());
			}
			request.setAttribute("book_type_list", bookTypeList);
			request.getRequestDispatcher("webpages/admin/booktype/booktype_list.jsp").forward(request, response);
			break;
			
			
		case "libr_add_book_type":
			
			BookType libr_booktype = readBookType(request);
			service.saveBookType(libr_booktype);
			
			List<BookType> libr_bookTypeList = service.findAllBooktype();
			for(BookType list:libr_bookTypeList)
			{
				System.out.println(list.getName());
			}
			request.setAttribute("book_type_list", libr_bookTypeList);
			request.getRequestDispatcher("webpages/librarian/booktype/booktype_list.jsp").forward(request, response);
			break;
			
		case "list_book_type":
			
			List<BookType> booktypelist = service.findAllBooktype();
			for(BookType list:booktypelist)
			{
				System.out.println(list.getName());
			}
			
			/*BookType bookType = readBookType(request);*/
			
			request.getParameter("book_type_list");
			
			
			request.getRequestDispatcher("webpages/admin/booktype/booktype_list.jsp").forward(request, response);
			break;
			
		case "update_book_type":
			
			int id = Integer.parseInt(request.getParameter("id"));
			BookType bookType2 = readBookType(request);
			bookType2.setId(id);
			service.updateBookType(bookType2);
			List<BookType> bookTypeList2 = service.findAllBooktype();
			request.setAttribute("book_type_list", bookTypeList2);
			request.getRequestDispatcher("webpages/admin/booktype/booktype_list.jsp").forward(request, response);
			break;
			
		case "libr_update_book_type":
			
			int lid = Integer.parseInt(request.getParameter("id"));
			BookType libr_bookType2 = readBookType(request);
			libr_bookType2.setId(lid);
			service.updateBookType(libr_bookType2);
			List<BookType> libr_bookTypeList2 = service.findAllBooktype();
			request.setAttribute("book_type_list", libr_bookTypeList2);
			request.getRequestDispatcher("webpages/librarian/booktype/booktype_list.jsp").forward(request, response);
			break;
			default:
				request.getRequestDispatcher("pages-404-withoutmenus.html").forward(request, response);
		}
	}

	private BookType readBookType(HttpServletRequest request) {
		// TODO Auto-generated method stub
		BookType bookType = new BookType();
		bookType.setName(request.getParameter("name"));
		
		return bookType;
	}

}
