package com.bookcycle.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookcycle.domain.BookRequest;
import com.bookcycle.domain.BookType;
import com.bookcycle.domain.Lending;
import com.bookcycle.domain.LendingReq;
import com.bookcycle.domain.Librarian;
import com.bookcycle.domain.Library;
import com.bookcycle.domain.LibraryBook;
import com.bookcycle.domain.User;
import com.bookcycle.domain.UserBook;
import com.bookcycle.service.BookRequestService;
import com.bookcycle.service.BookTypeService;
import com.bookcycle.service.LendingReqService;
import com.bookcycle.service.LendingService;
import com.bookcycle.service.LibrarianService;
import com.bookcycle.service.LibraryBookService;
import com.bookcycle.service.LibraryService;
import com.bookcycle.service.UserBookService;
import com.bookcycle.service.UserService;
import com.bookcycle.service.impl.BookRequestServiceImpl;
import com.bookcycle.service.impl.BookTypeServiceImpl;
import com.bookcycle.service.impl.LendingReqServiceImpl;
import com.bookcycle.service.impl.LendingServiceImpl;
import com.bookcycle.service.impl.LibrarianServiceImpl;
import com.bookcycle.service.impl.LibraryBookServiceImpl;
import com.bookcycle.service.impl.LibraryServiceImpl;
import com.bookcycle.service.impl.UserBookServiceImpl;
import com.bookcycle.service.impl.UserServiceImpl;

/**
 * Servlet implementation class PageControllerServlet
 */
@WebServlet("/PageControllerServlet")
public class PageControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String next_page = request.getParameter("page");
	
		HttpSession session = request.getSession();
		Librarian logged_libr_list = (Librarian) session.getAttribute("logged_libr");
		
		switch(next_page)
		{
			
		case "home":
			
			request.getRequestDispatcher("webpages/admin/home/home.jsp").forward(request, response);
			break;
			
		case "booktype":
			
			BookTypeService service = new BookTypeServiceImpl();
			List<BookType> booktypelist = service.findAllBooktype();
			request.setAttribute("book_type_list", booktypelist);
			request.getRequestDispatcher("webpages/admin/booktype/booktype_list.jsp").forward(request, response);
			break;
			
		case "libr_booktype":
			
			BookTypeService libr_service = new BookTypeServiceImpl();
			List<BookType> libr_booktypelist = libr_service.findAllBooktype();
			request.setAttribute("book_type_list", libr_booktypelist);
			request.getRequestDispatcher("webpages/librarian/booktype/booktype_list.jsp").forward(request, response);
			break;
		
		case "booktype_add":

			request.getRequestDispatcher("webpages/admin/booktype/booktype_add.jsp").forward(request, response);
			break;
			
		case "library":

			LibraryService libservice = new LibraryServiceImpl();
			List<Library> librarylist = libservice.findAllLibrary();
			request.setAttribute("library_list", librarylist);
			request.getRequestDispatcher("webpages/admin/library/library_list.jsp").forward(request, response);
			break;
			
		case "library_add":
			
			request.getRequestDispatcher("webpages/admin/library/library_add.jsp").forward(request, response);
			break;
			
		case "librarian_add":
			
			LibraryService libservice1 = new LibraryServiceImpl();
			List<Library> lib_list = libservice1.findAllLibrary();
			request.setAttribute("lib_list", lib_list);
			request.getRequestDispatcher("webpages/admin/librarian/librarian_add.jsp").forward(request, response);
			break;
			
		case "librarian":
			
			LibrarianService librservice = new LibrarianServiceImpl();
			List <Librarian> librarianList = librservice.findAllLibrarian();
			request.setAttribute("librarian_list", librarianList);
			request.getRequestDispatcher("webpages/admin/librarian/librarian_list.jsp").forward(request, response);
			break;
			
		case "library_book_add":
			
			LibraryService libservice2 = new LibraryServiceImpl();
			List<Library> lib_list2 = libservice2.findAllLibrary();
			/*for(Library lib_id : lib_list2)
			{
				
				if(logged_lib_id == lib_id.getId())
				{
					
				}
			}*/
			BookTypeService bktypservice = new BookTypeServiceImpl();
			List<BookType> bktyp_list2 = bktypservice.findAllBooktype();
			request.setAttribute("bktyp_list", bktyp_list2);
			request.setAttribute("lib_list", lib_list2);
			request.getRequestDispatcher("webpages/admin/librarybook/librarybook_add.jsp").forward(request, response);
			break;
			
		case "libr_library_book_add":
			
			//logged_libr_list.forEach(obj ->System.out.println(obj));
			/*for(Librarian lib_obj : logged_libr_list)
			{
				System.out.println(lib_obj);
			}*/
			int logged_lib_id = logged_libr_list.getLibrary().getId();
			System.out.println("Login Id"+logged_lib_id);
			LibraryService libr_libservice2 = new LibraryServiceImpl();
			List<Library> libr_lib_list2 = libr_libservice2.findAllLibrary();
			List<Library> libr_lib_list3 = new ArrayList<Library>();
			for(Library lib_id : libr_lib_list2)
			{
				if(logged_lib_id == lib_id.getId())
				{
					System.out.println("ifffffffff"+logged_lib_id+lib_id.getId());
					libr_lib_list3.add(lib_id);
					System.out.println("list111111111"+libr_lib_list3);
				}
			}
			BookTypeService libr_bktypservice = new BookTypeServiceImpl();
			List<BookType> libr_bktyp_list2 = libr_bktypservice.findAllBooktype();
			request.setAttribute("bktyp_list", libr_bktyp_list2);
			request.setAttribute("lib_list", libr_lib_list3);
			request.getRequestDispatcher("webpages/librarian/librarybook/librarybook_add.jsp").forward(request, response);
			break;
			
		case "library_book":
			
			LibraryBookService libbkservice = new LibraryBookServiceImpl();
			List <LibraryBook> libraryBookList = libbkservice.findAllLibraryBook();
			request.setAttribute("librarybook_list", libraryBookList);
			request.getRequestDispatcher("webpages/admin/librarybook/librarybook_list.jsp").forward(request, response);
			break;
			
		case "user_book":
			
			UserBookService userservice = new UserBookServiceImpl();
			List<UserBook> userBookList = userservice.findAllUserBook();
			request.setAttribute("userbook_list", userBookList);
			request.getRequestDispatcher("webpages/admin/userbook/userbook_list.jsp").forward(request, response);
			break;
			
		case "libr_library_book":
			
			int logged_lib_id2 = logged_libr_list.getLibrary().getId();
			System.out.println("Login Id"+logged_lib_id2);
			LibraryBookService libr_libbkservice = new LibraryBookServiceImpl();
			List <LibraryBook> libr_libraryBookList = libr_libbkservice.findAllBookByLibraryId(logged_lib_id2);
			List<LibraryBook> libr_libraryBookList2 = libr_libraryBookList.stream().filter(list->list.getStatus()==1).collect(Collectors.toList());
			//List <LibraryBook> libr_libraryBookList2 = new ArrayList<LibraryBook>();
			/*for(LibraryBook book_id : libr_libraryBookList)
			{
			}*/
			request.setAttribute("librarybook_list", libr_libraryBookList2);
			request.getRequestDispatcher("webpages/librarian/librarybook/librarybook_list.jsp").forward(request, response);
			break;
			
		case "book_requests":
			
			BookRequestService bkreqservice = new BookRequestServiceImpl();
			List<BookRequest> bookRequestList = bkreqservice.findAllBookRequest();
			request.setAttribute("bookrequest_list",bookRequestList);
			request.getRequestDispatcher("webpages/admin/bookrequest/bookrequest_list.jsp").forward(request, response);
			break;
	
		case "libr_lend_books":
			
			int logged_lib_id3 = logged_libr_list.getLibrary().getId();
			LibraryBookService librbkservice = new LibraryBookServiceImpl();
			UserService usrservice = new UserServiceImpl();
			List<LibraryBook> libr_book_list = librbkservice.findAllBookByLibraryId(logged_lib_id3);
			request.setAttribute("libr_book_list", libr_book_list);
			List<User> usr_list = usrservice.findAllUser();
			request.setAttribute("user_list", usr_list);
			request.getRequestDispatcher("webpages/librarian/lending/lending_add.jsp").forward(request, response);
			break;
		
		case "lended_books":
			
			LendingService lndnservice = new LendingServiceImpl();
			List<Lending> lending_list = lndnservice.findAllLending();
			List<Lending> lending_list_pending = lending_list.stream().filter(list->list.getLending_status()==1).collect(Collectors.toList());
			request.setAttribute("pending_list", lending_list_pending);
			request.getRequestDispatcher("webpages/librarian/lending/lending_list.jsp").forward(request, response);
			break;
			
		case "records":
			int logged_lib_id4 = logged_libr_list.getLibrary().getId();
			System.out.println(logged_lib_id4);
			LendingService lndngservice = new LendingServiceImpl();
			List<Lending> lending_list1 = lndngservice.findAllLending();
			List<Lending> lending_records = lending_list1.stream().filter(list -> list.getLending_status()==0 && list.getBook().getLibrary().getId()==logged_lib_id4).collect(Collectors.toList());
			request.setAttribute("records_list", lending_records);
			request.getRequestDispatcher("webpages/librarian/records/records.jsp").forward(request, response);
			break;	
			
		case "lend_req":
			int logged_lib_id5 = logged_libr_list.getLibrary().getId();
			LendingReqService lnd_req_service = new LendingReqServiceImpl();
			List<LendingReq> lending_req_list = lnd_req_service.findAllLendingReq();
			List<LendingReq> lending_requests = lending_req_list.stream().filter(list->list.getBook().getLibrary().getId()==logged_lib_id5).collect(Collectors.toList());
			request.setAttribute("lending_request_list", lending_requests);
			request.getRequestDispatcher("webpages/librarian/lending_req/requests.jsp").forward(request, response);
			break;
			
			
		case "logout":
			
			session.invalidate();
			response.sendRedirect("webpages/pages-signin.jsp");
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
	}

}
