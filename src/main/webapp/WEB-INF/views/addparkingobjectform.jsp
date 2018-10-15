<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="Shortcut icon" href="${pageContext.request.contextPath}/resources/pictures/BATI_LOGO_KULA_RED_bez_tla.ico" />
	<title>Adding new parking object</title>
</head>
<body>

	<div class="horizontal-center" style="width: 10%; min-width:399px; top: 20; margin: 0 auto;">
		<h1><span class="label label-default">Dodawanie nowego obiektu</span></h1>
	</div>

	<div style="width: 50%; height: 100vh; margin: 0 auto;">
		<div style="height: 10%; min-height: 550px; margin-top: 10%;">
			<form:form action="${pageContext.request.contextPath}/manageparkings/addfilledparkingobjectform" modelAttribute="newParkingObject" method="post">
		
			<div class="form-group">
				 <label for="comment">Nazwa obiektu:</label> 
				 <form:input path="name" id="name" class="form-control" required="required"></form:input>
				 <!--<c:if test="${pageContext.request.method=='POST'}"><form:errors path="${newnote.adviceName}" /></c:if>-->
			 </div>
			
			 <div class="form-group">
				 <label for="comment">Adres obiektu:</label> 
				 <form:textarea path="address" id="address" class="form-control" rows="2" required="required"></form:textarea>
				 <!--<c:if test="${pageContext.request.method=='POST'}"><form:errors path="${newnote.adviceDescription}" /></c:if>-->
			 </div>
			 
 			 <div class="form-group">
				 <label for="comment">Kontakt:</label> 
				 <form:textarea path="contact" id="contact" class="form-control" rows="2"></form:textarea>
				 <!--<c:if test="${pageContext.request.method=='POST'}"><form:errors path="${newnote.adviceDescription}" /></c:if>-->
			 </div>
			 
  			 <div class="form-group">
				 <label for="comment">Opis:</label> 
				 <form:textarea path="description" id="description" class="form-control" rows="5"></form:textarea>
				 <!--<c:if test="${pageContext.request.method=='POST'}"><form:errors path="${newnote.adviceDescription}" /></c:if>-->
			 </div>
			 			
			 <input class="btn btn-success" type="submit" value = "Zapisz" />
			 <input class="btn btn-warning" type="button" value = "Wróć" onclick="window.location.href='${pageContext.request.contextPath}/manageparkings/'"/>
			</form:form>
		</div>
</div>
</body>
</html>