<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Form Page</title>

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
          <a class="nav-link active" aria-current="page" href="listPage">Customers list page</a>
        </li>
       </ul>
       </div>
   </div> 
</nav>
</header>
<br><br>
<div class="container" style="width:50%">
	
	<c:if test="${customer == null }"> 		<!--When data is not coming from servlet  -->
		<form action="add" method="post">
		<h2 align="center">Customer Form</h2><hr><br>
		<p>Please fill out the information below to register as a new customer</p>
</c:if>

	<c:if test="${customer != null }">		<!--When data is coming from servlet  -->
		<form action="update" method="post">
		<h2>Customer Edit Form</h2>
		<hr>
</c:if>

	<div class="mb-3" hidden>
  		<input  value="<c:out value="${customer.id }"></c:out>" type="text" class="form-control" id="exampleFormControlInput" name="tbId">
</div>
	<div class="mb-3">
  		<input type="text" value="<c:out value="${customer.name }"></c:out>" class="form-control" id="exampleFormControlInput1" name="tbName" placeholder="Name" required="required">
</div>
	<div class="mb-3">
  		<input type="text" value="<c:out value="${customer.city }"></c:out>" class="form-control" id="exampleFormControlInput2" name="tbCity" placeholder="City" required="required">
</div>
	<div class="mb-3">
  		<input type="email" value="<c:out value="${customer.email }"></c:out>" class="form-control" id="exampleFormControlInput3" name="tbEmail" placeholder="Email address" required="required">
</div>
	<div class="mb-3">
  		<input type="tel" value="<c:out value="${customer.contact }"></c:out>" class="form-control" id="exampleFormControlInput4" name="tbContact" placeholder="Contact no" required="required">
</div>
	<div>
		<button class="btn btn-info">Save</button>
</div>
</form>
</div>
</body>
</html>