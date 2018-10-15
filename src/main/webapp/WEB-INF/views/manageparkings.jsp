<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="Shortcut icon" href="${pageContext.request.contextPath}/resources/pictures/BATI_LOGO_KULA_RED_bez_tla.ico" />
	<title>manageparkings</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%@include file="includes/header_top.jsp" %>
	<div class="horizontal-center" style="width: 10%; min-width: 312px; top: 20; margin: 0 auto;">
		<h1><span class="label label-default">Zarządzaj parkingami</span></h1>
	</div>
	<%@include file="includes/header_bottom.jsp" %>
	
	<div class="row" style="padding-top: 20px;">
		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<div class="row" style="margin-bottom: 20px;">
				<security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERADMIN')">	
					<div style = "padding-left: 50px"><button class="btn btn-success" onclick="window.location.href='${pageContext.request.contextPath}/manageparkings/addparkingobject'">Dodaj obiekt</button></div>
				</security:authorize>
			</div>
			<div class = "row">
					<div class="panel-group" id="accordion">		
					<c:forEach var="parkingObject" items="${parkingObjects}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="panel-title">
									<security:authorize access="hasRole('ROLE_SUPERADMIN')">				
										<button class="btn btn-danger btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/manageparkings/deleteparkingobject?parkingObjectId=${parkingObject.id}'">Usun</button>
									</security:authorize>
									<security:authorize access="hasRole('ROLE_ADMIN')  or hasRole('ROLE_SUPERADMIN')">
										<button class="btn btn-warning btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/manageparkings/editparkingobject?parkingObjectId=${parkingObject.id}'">Edytuj</button>				
									</security:authorize>
									<a data-toggle="collapse" data-parent="#accordion" href="#${parkingObject.id}">${parkingObject.name}</a>
								</div>
							</div>
							<div id="${parkingObject.id}" class="panel-collapse collapse">
								<div class="panel-body">
								    <div class="panel panel-success">
							    		<div class="panel-heading">Adres</div>
								      	<div class="panel-body">${parkingObject.address}</div>
								    </div>
								
								    <div class="panel panel-info">
								      	<div class="panel-heading">Kontakt</div>
								      	<div class="panel-body">${parkingObject.contact}</div>
								    </div>
								
								    <div class="panel panel-warning">
								      	<div class="panel-heading">Opis</div>
								      	<div class="panel-body">${parkingObject.description}</div>
								    </div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="col-lg-2"></div>
	</div>
</body>
</html>