
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library List</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../nav/nav.jsp"></jsp:include>

</head>




<body>
<body>



	<section role="main" class="content-body"> <header
		class="page-header">
	<h2>Advanced Tables</h2>


	</header> <!-- start: page -->

	<div class="panel-body">
		<div id="datatable-default_wrapper"
			class="dataTables_wrapper no-footer"></div>
		<div class="table-responsive">
			<table
				class="table table-bordered table-striped mb-none dataTable no-footer"
				id="datatable-default" role="grid"
				aria-describedby="datatable-default_info">
				<thead>
					<tr role="row">
						<th class="sorting_asc" tabindex="0"
							aria-controls="datatable-default" rowspan="1" colspan="1"
							aria-sort="ascending"
							aria-label="Rendering engine: activate to sort column ascending"
							style="width: 200px;">Name</th>
							<th class="sorting_asc" tabindex="0"
							aria-controls="datatable-default" rowspan="1" colspan="1"
							aria-sort="ascending"
							aria-label="Rendering engine: activate to sort column ascending"
							style="width: 200px;">Address</th>
							<th class="sorting_asc" tabindex="0"
							aria-controls="datatable-default" rowspan="1" colspan="1"
							aria-sort="ascending"
							aria-label="Rendering engine: activate to sort column ascending"
							style="width: 200px;">Pincode</th>
							<th class="sorting_asc" tabindex="0"
							aria-controls="datatable-default" rowspan="1" colspan="1"
							aria-sort="ascending"
							aria-label="Rendering engine: activate to sort column ascending"
							style="width: 200px;">District</th>
							<th class="sorting_asc" tabindex="0"
							aria-controls="datatable-default" rowspan="1" colspan="1"
							aria-sort="ascending"
							aria-label="Rendering engine: activate to sort column ascending"
							style="width: 200px;">Phone No.</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${library_list}">
						<tr>
							<td>${list.name}</td>
							<td>${list.address}</td>
							<td>${list.pincode}</td>
							<td>${list.district}</td>
							<td>${list.phone}</td>
							<td><a
								href="LibraryController?command=findLibrary&id=${list.id}"><button
										type="button" class="btn btn-primary" role="button">Edit Library</button></a></td>
							<td><a
								href="LibraryController?command=deleteLibrary&id=${list.id }">
									<button name="delete" class=" btn btn-lg btn-danger">Delete</button>
							</a></td>
						</tr>
					</c:forEach>
					

				</tbody>
			</table>
		</div>
		<!-- <div class="row datatables-footer">
			<div class="col-sm-12 col-md-6">
				<div class="dataTables_info" id="datatable-default_info"
					role="status" aria-live="polite">Showing 1 to 10 of 57
					entries</div>
			</div>
			<div class="col-sm-12 col-md-6">
				<div class="dataTables_paginate paging_bs_normal"
					id="datatable-default_paginate">
					<ul class="pagination">
						<li class="prev disabled"><a href="#"><span
								class="fa fa-chevron-left"></span></a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li class="next"><a href="#"><span
								class="fa fa-chevron-right"></span></a></li>
					</ul>
				</div>
			</div>
		</div> -->
	</div>
	</div>
	<!-- end: page --> </section>


</body>
<jsp:include page="../header/header.jsp"></jsp:include>
</html>
