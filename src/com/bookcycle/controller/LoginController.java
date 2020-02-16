package com.bookcycle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookcycle.domain.Admin;
import com.bookcycle.domain.Librarian;
import com.bookcycle.service.AdminService;
import com.bookcycle.service.LibrarianService;
import com.bookcycle.service.impl.AdminServiceImpl;
import com.bookcycle.service.impl.LibrarianServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String role = request.getParameter("role");
		System.out.println(role);
		String usrnm = request.getParameter("username");
		String pwd = request.getParameter("password");
		boolean login = true;
		switch(role)
		{
		case "admin":
			
			AdminService service = new AdminServiceImpl();
			List<Admin> login_det = service.findAllAdmin();
			for(Admin list : login_det)
			{
				if(list.getUsername().equals(usrnm) && list.getPassword().equals(pwd))
				{
					if(!response.isCommitted())
					{
						request.setAttribute("librarian", list);
						request.getRequestDispatcher("webpages/admin/home/home.jsp").forward(request, response);
					}
					
				}
				else
				{
					if(!response.isCommitted())
					{
						login = false;
					}
				}					
			}
			if(login == false)
			{
				response.sendRedirect("webpages/pages-signin.jsp");
			}
			break;
			
		case "librarian":
			
			
			LibrarianService lib_service = new LibrarianServiceImpl();
			List<Librarian> libr_login = lib_service.findAllLibrarian();
			Librarian li=new Librarian();
			System.out.println("*"+libr_login);
			for(Librarian libr_list : libr_login)
			{
				System.out.println(libr_list);
				if(libr_list.getEmail().equals(usrnm) && libr_list.getPassword().equals(pwd))
				{
					if(!response.isCommitted())
					{
						li=libr_list;
						HttpSession session = request.getSession();
						session.setAttribute("logged_libr", li);
						request.getRequestDispatcher("webpages/librarian/home/home.jsp").forward(request, response);
					}
				}
				else
				{
					if(!response.isCommitted())
					{
						login = false;
					}
				}
			}
			if(login == false)
			{
				response.sendRedirect("webpages/pages-signin.jsp");
			}
			break;
			
			default: 
				request.getRequestDispatcher("webpages/pages-404-withoutmenus.html").forward(request, response);
		}
		
	}

}
