<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	 
        <section class="body">

      <!-- start: header -->
       <header class="header">
        <div class="logo-container">
          <a href="../" class="logo">
            <img src="${pageContext.servletContext.contextPath}/assets/images/logo.png" height="35" alt="Porto Admin" />
          </a>
          <div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
            <i class="fa fa-bars" aria-label="Toggle sidebar"></i>
          </div>
        </div>
      
      <!--   start: search & user box -->
        <div class="header-right">
      
          <form action="pages-search-results.html" class="search nav-form">
            <div class="input-group input-search">
              <input type="text" class="form-control" name="q" id="q" placeholder="Search...">
              <span class="input-group-btn">
                <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
              </span>
            </div>
          </form>
      
          <span class="separator"></span>
      

      

      
          <div id="userbox" class="userbox">
            <a href="#" data-toggle="dropdown">
              <%-- <figure class="profile-picture">
                <img src="${pageContext.servletContext.contextPath}/assets/images/!logged-user.jpg" alt="Joseph Doe" class="img-circle" data-lock-picture="assets/images/!logged-user.jpg" />
              </figure> --%>
              <div class="profile-info" data-lock-name="John Doe" data-lock-email="johndoe@okler.com">
                <b><span class="name">ADMIN</span></b>
                
              </div>
      
              <i class="fa custom-caret"></i>
            </a>
      
            <div class="dropdown-menu">
              <ul class="list-unstyled">
                <li class="divider"></li>
                <li>
                  <a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/PageControllerServlet?page=logout"><i class="fa fa-power-off"></i> Logout</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
<!--         end: search & user box -->
      </header>  
      <!-- end: header -->

       <div class="inner-wrapper">
    <!--     start: sidebar -->
        <aside id="sidebar-left" class="sidebar-left">
        
          <div class="sidebar-header">
            <div class="sidebar-title">
              Navigation
            </div>
            <div class="sidebar-toggle hidden-xs" data-toggle-class="sidebar-left-collapsed" data-target="html" data-fire-event="sidebar-left-toggle">
              <i class="fa fa-bars" aria-label="Toggle sidebar"></i>
            </div>
          </div>
        
          <div class="nano">
            <div class="nano-content">
              <nav id="menu" class="nav-main" role="navigation">
                <ul class="nav nav-main">
                  <li>
                    <a href="${pageContext.servletContext.contextPath}/webpages/admin/home/home.jsp">
                      <i class="fa fa-home" aria-hidden="true"></i>
                      <span>Home</span>
                    </a>
                  </li>
                  <%-- <li class="nav-parent nav-expanded nav-active">
                    <a>
                      <i class="fa fa-list-alt" aria-hidden="true"></i>
                      <span>Book Type</span>
                    </a>
                    <ul class="nav nav-children">
                    <li class="nav-active">
						<a href="${pageContext.servletContext.contextPath}/webpages/admin/booktype/booktype_add.jsp">
							 Add
						</a>
					</li>
					<li class="nav-active">
						<a href="${pageContext.servletContext.contextPath}/PageControllerServlet?page=booktype">
							View List	
						</a>
					</li>
                  </ul>
                  </li> --%>
                  
                  <li class="nav-parent nav-expanded nav-active">
                    <a>
                      <i class="fa fa-list-alt" aria-hidden="true"></i>
                      <span>Library</span>
                    </a>
                    <ul class="nav nav-children">
                    <li class="nav-active">
						<a href="${pageContext.servletContext.contextPath}/webpages/admin/library/library_add.jsp">
							 Add
						</a>
					</li>
					<li class="nav-active">
						<a href="${pageContext.servletContext.contextPath}/PageControllerServlet?page=library">
							View List	
						</a>
					</li>
                  </ul>
                  </li>
               
               <li class="nav-parent nav-expanded nav-active">
                    <a>
                      <i class="fa fa-list-alt" aria-hidden="true"></i>
                      <span>Librarian</span>
                    </a>
                    <ul class="nav nav-children">
                    <li class="nav-active">
						<a href="${pageContext.servletContext.contextPath}/PageControllerServlet?page=librarian_add">
							 Add
						</a>
					</li>
					<li class="nav-active">
						<a href="${pageContext.servletContext.contextPath}/PageControllerServlet?page=librarian">
							View List	
						</a>
					</li>
                  </ul>
                  </li>
                  
                  <li class="nav-parent nav-expanded nav-active">
                    <a>
                      <i class="fa fa-list-alt" aria-hidden="true"></i>
                      <span>Books</span>
                    </a>
                    <ul class="nav nav-children">
					<li class="nav-active">
						<a href="${pageContext.servletContext.contextPath}/PageControllerServlet?page=library_book">
							Library Books	
						</a>
					</li>
                  </ul>
                  <ul class="nav nav-children">
					<li class="nav-active">
						<a href="${pageContext.servletContext.contextPath}/PageControllerServlet?page=user_book">
							User Books	
						</a>
					</li>
                  </ul>
                  </li> 
                  
                  <li class="nav-parent nav-expanded nav-active">
                    <a>
                      <i class="fa fa-list-alt" aria-hidden="true"></i>
                      <span>Book Requests</span>
                    </a>
                    <ul class="nav nav-children">
					<li class="nav-active">
						<a href="${pageContext.servletContext.contextPath}/PageControllerServlet?page=book_requests">
							View List	
						</a>
					</li>
                  </ul>
                  </li>  
            
            </div>
        
          </div>
        
        </aside>  
  </body>
</html>  