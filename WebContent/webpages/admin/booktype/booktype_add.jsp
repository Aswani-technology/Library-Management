<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../header/header.jsp"></jsp:include>
<jsp:include page="../nav/nav.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Booktype</title>
</head>
<body>
<section role="main" class="content-body">
          <header class="page-header">
            <h2>Form Validation</h2>
          
            <div class="right-wrapper pull-right">
              <ol class="breadcrumbs">
                <li>
                  <a href="index.html">
                    <i class="fa fa-home"></i>
                  </a>
                </li>
                <li><span>Forms</span></li>
                <li><span>Validation</span></li>
              </ol>
          
              <a class="sidebar-right-toggle" data-open="sidebar-right"><i class="fa fa-chevron-left"></i></a>
            </div>
          </header>

          <!-- start: page -->
          <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
              <form id="form" action="${pageContext.servletContext.contextPath}/BookTypeController" method="post" class="form-horizontal">
                <section class="panel">
                  <header class="panel-heading">
                    <div class="panel-actions">
                      <a href="#" class="fa fa-caret-down"></a>
                      <a href="#" class="fa fa-times"></a>
                    </div>

                    <h2 class="panel-title">Add New Book Type</h2>
                    <p class="panel-subtitle">
                     <!--  Basic validation will display a label with the error after the form control. -->
                    </p>
                  </header>
                  <div class="panel-body">
                    <div class="form-group">
                      <label class="col-sm-3 control-label">Book Type Name<span class="required">*</span></label>
                      <div class="col-sm-9">
                        <input type="text" name="name" class="form-control" placeholder="Enter Book Type.." required="">
                      </div>
                    </div>
                  </div>
                   <input type="hidden" name="command" value="add_book_type">
                  <footer class="panel-footer">
                    <div class="row">
                      <div class="col-sm-9 col-sm-offset-3">
                        <button class="btn btn-primary" type="submit">Submit</button>
                        <button type="reset" class="btn btn-default">Reset</button>
                      </div>
                    </div>
                  </footer>
                </section>
              </form>
            </div>
           
          </div>
         
 
          </section>
</body>
</html>