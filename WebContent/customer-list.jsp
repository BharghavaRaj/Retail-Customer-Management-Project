<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- what is the use of prefix 
 breaf:  Within <> we are writing html and jstl, then browser will get confuse for that differentiation between jstl and html for we using prefix-->
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customers List Page</title>

<!-- CDN(Content Delivery Network) -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>
	<header>
		<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
 			<div class="container-fluid">
    			<a class="navbar-brand mb-0 h1" href="#">Customer Management System</a>
    	<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      		<span class="navbar-toggler-icon"></span>
    </button>
    		<div class="collapse navbar-collapse" id="navbarSupportedContent">
      	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
        	<li class="nav-item">
          		<a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/listPage">Home</a>
        	</li>
        </ul>
	</div>	
</div>
</nav>	
</header>
<br>
<br>
<div class="container">
	<h2 align="center">Customers List</h2>
	<hr><br>
	<form action="formPage">
	<button type="submit" class="btn btn-info">Add New</button>
</form>
<br>
		<table class="table table-bordered">
  <thead class="table-dark">
    <tr>
      <th style="width:5%" scope="col">ID</th>
      <th style="width:20%" scope="col">Name</th>
      <th style="width:20%" scope="col">City</th>
      <th style="width:25%" scope="col">Email</th>
      <th style="width:10%"scope="col">Contact</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="i" items="${al}">
    <tr>
      <th scope="row"><c:out value="${i.id }"></c:out></th>
      <td><c:out value="${i.name }"></c:out></td>
      <td><c:out value="${i.city }"></c:out></td>
      <td><c:out value="${i.email }"></c:out></td>
      <td><c:out value="${i.contact }"></c:out></td>
      <td class="table">
      						<!-- Query String -->
      		<a href="edit?id=<c:out value="${i.id}"></c:out>" class="btn btn-primary btn-sm text-decoration-none">
  			Edit
  		</a>&nbsp;&nbsp;&nbsp;&nbsp;
  			<a href="delete?id=<c:out value="${i.id}"></c:out>" class="btn btn-danger btn-sm text-decoration-none">
  			Delete
  		</a>		
  	</td>		
    </tr>
   </c:forEach> 
  </tbody>
</table>
</div>
</body>
</html>