<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header/header.jsp"></jsp:include>
<jsp:include page="../nav/nav.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Librarian</title>
<!-- <script type="text/javascript">
function validate(){

	var pattern = new RegExp("^[0-9]{10}$");
	var pin_pattern = new RegExp("^[0-9]{6}$")

	 if ((pattern.test(document.getElementById('contact').value)) && (pin_pattern.test(document.getElementById('pincode')).value)) {
	        return true;
	    }
	    else {
	    	alert("Please Check Fields...");
	        return false;
	        
	    } 
	
	}

</script>
 -->
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
              <form id="form" action="${pageContext.servletContext.contextPath}/LibrarianController" method="post" class="form-horizontal" onsubmit="return validate()">
                <section class="panel">
                  <header class="panel-heading">
                    <div class="panel-actions">
                      <a href="#" class="fa fa-caret-down"></a>
                      <a href="#" class="fa fa-times"></a>
                    </div>

                    <h2 class="panel-title">Add New Librarian</h2>
                    <p class="panel-subtitle">
                     <!--  Basic validation will display a label with the error after the form control. -->
                    </p>
                  </header>
                  <div class="panel-body">
                    <div class="form-group">
                      <label class="col-sm-3 control-label">First Name<span class="required">*</span></label>
                      <div class="col-sm-9">
                        <input type="text" name="f_name" class="form-control" placeholder="Enter First Name.." required="">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label">Last Name<span class="required">*</span></label>
                      <div class="col-sm-9">
                        <input type="text" name="l_name" class="form-control" placeholder="Enter Last Name.." required="">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-3 control-label">Contact<span class="required">*</span></label>
                      <div class="col-sm-9">
                        <input type="text" id="contact" name="contact" class="form-control" placeholder="Enter Contact.." required="" maxlength="10">
                      </div>
                    </div>
           <div class="form-group">
			<label class="col-sm-3 control-label">Email <span class="required">*</span></label>
				<div class="col-sm-9">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="fa fa-envelope"></i>
						</span>
						<input type="email" name="email" class="form-control" placeholder="eg.: email@email.com" required/>
					</div>
				</div>
				<div class="col-sm-9">
				</div>
			</div>
            
                      <div class="form-group">
                      <label class="col-sm-3 control-label">Password<span class="required">*</span></label>
                      <div class="col-sm-9">
                        <input type="password" name="password" class="form-control" placeholder="Enter Password.." required="">
                      </div>
                    </div>
                   <div class="form-group">
                      <label class="col-sm-3 control-label">Address<span class="required">*</span></label>
                      <div class="col-sm-9">
                        <input type="text" name="address" class="form-control" placeholder="Enter Address.." required="">
                      </div>
                    </div>
                   <div class="form-group">
                      <label class="col-sm-3 control-label">Pincode<span class="required">*</span></label>
                      <div class="col-sm-9">
                        <input type="text" id="pincode" name="pincode" class="form-control" placeholder="Enter Pincode.." required="" maxlength="6">
                      </div>
                    </div>
                    
                    
						<div class="form-group">
							<label class="col-sm-3 control-label">District</label>
								<div class="col-sm-9">
									<select name="district" class="form-control" required>
										<option value="">Select District</option>
										<option value="AlAPPUZHA">Alappuzha</option>
										<option value="ERNAMKULAM">Ernamkulam</option>
										<option value="IDUKKI">Idukki</option>
										<option value="KANNUR">Kannur</option>
										<option value="KASARGOD">Kasargod</option>
										<option value="KOLLAM">Kollam</option>
										<option value="KOTTAYAM">Kottayam</option>
										<option value="KOZHIKODE">Kozhikode</option>
										<option value="MALAPPURAM">Malappuram</option>
										<option value="PALAKKAD">Palakkad</option>
										<option value="PATHANAMTHITTA">Pathanamthitta</option>
										<option value="THIRUVANANTHAPURAM">Thiruvananthapuram</option>
										<option value="THRISSUR">Thrissur</option>
										<option value="WAYANAD">Wayanad</option>
									</select>
									<label class="error" for="district"></label>
								</div>
							</div>
                    
               
                  <div class="form-group">
                  <label class="col-sm-3 control-label">Status</label>
                  <div class="col-sm-9">
                  <select class="form-control" name="status">
                  		<option value="">Select Status</option>
						<option value="1">Available</option>
						<option value="0">Not Available</option>
					</select>
					<label class="error" for="status"></label>
					</div>
					</div>
				
				  <div class="form-group">
                  <label class="col-sm-3 control-label">Library</label>
                  <div class="col-sm-9">
                  <select class="form-control" name="library">
                  <option value="">Select Library</option>
                  <c:forEach var="list" items="${lib_list}">
                  		<option value="${list.id}">${list.name}</option>
                  		</c:forEach>
					</select>
					<label class="error" for="library"></label>
					</div>
					</div>
					</div>
                   <input type="hidden" name="command" value="add_librarian">
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