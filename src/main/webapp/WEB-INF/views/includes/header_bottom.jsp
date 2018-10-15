<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
	<!--<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"> -->
	<!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
</head>
<body>
	<nav style="top: 20px; min-width: 1000px;" class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="${pageContext.request.contextPath}/">Parking Service</a>
	    </div>
	    <ul class="nav navbar-nav">
	    
	      	<li> <!-- class="active" --><a href="${pageContext.request.contextPath}/">Home</a></li>
		 	<li><a href="${pageContext.request.contextPath}/manageparkings/">Zarządzaj parkingami</a></li>
		    <li><a href="${pageContext.request.contextPath}/managedevices/">Zarządzaj urządzeniami</a></li>
			<li><a href="#">Zarządzaj wizytami</a></li>
			<li><a href="#">Zarządzaj technikami</a></li>
			
			<!--<li class="dropdown">
			  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Menu 3
			  <span class="caret"></span></a>
			  <ul class="dropdown-menu">
			    <li><a href="#">option 1</a></li>
			    <li><a href="#">option 2</a></li>
			    <li><a href="#">option 3</a></li>
			  </ul>
			</li>-->
			
			
			<li class="dropdown">
			  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Extra
			  <span class="caret"></span></a>
			  <ul class="dropdown-menu">
			  	<li><a href="#">option 1</a></li>
			    <li><a href="#">opiton 2</a></li>
			    <li><a href="#">opiton 3</a></li>
			    <li><a href="#">opiton 4</a></li>
			    <li><a href="#">opiton 5</a></li>
			  </ul>
			</li>
			<security:authorize access="hasRole('ROLE_SUPERADMIN')">	
				<li><a href="${pageContext.request.contextPath}/additional/">Dodatkowe</a></li>	
			</security:authorize>
	    </ul>
	  </div>
	</nav>
</body>
</html>