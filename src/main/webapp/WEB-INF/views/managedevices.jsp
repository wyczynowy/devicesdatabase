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
	<title>managedevices</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%@include file="includes/header_top.jsp" %>
	<div class="horizontal-center" style="width: 10%; min-width: 345px; top: 20px; margin: 0 auto;">
		<h1><span class="label label-default">Zarządzaj urządzeniami</span></h1>
	</div>
	<%@include file="includes/header_bottom.jsp" %>
	
	<div class="row" style="padding-top: 20px;">
		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<div class="row" style="margin-bottom: 20px;">
				<security:authorize access="hasRole('ROLE_ADMIN')  or hasRole('ROLE_SUPERADMIN')">	
					<div style = "padding-left: 50px"><button class="btn btn-success" onclick="window.location.href='${pageContext.request.contextPath}/managedevices/adddevice'">Dodaj urządzenie</button></div>
				</security:authorize>
			</div>
			
			<div class = "row">
					<div class="panel-group" id="accordion">		
					<c:forEach var="device" items="${devices}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="panel-title">
									<security:authorize access="hasRole('ROLE_SUPERADMIN')">				
										<button class="btn btn-danger btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/managedevices/deletedevice?deviceId=${device.id}'">Usun</button>
									</security:authorize>
									<security:authorize access="hasRole('ROLE_ADMIN')  or hasRole('ROLE_SUPERADMIN')">
										<button class="btn btn-warning btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/managedevices/editdevice?deviceId=${device.id}'">Edytuj</button>				
									</security:authorize>
									<a data-toggle="collapse" data-parent="#accordion" href="#${device.id}">${device.deviceType}, serial no.: ${device.serial}</a>
								</div>
							</div>
							<div id="${device.id}" class="panel-collapse collapse">
								<div class="panel-body">
								
									<div class="panel panel-info">
										<div class="panel-heading">Obiekt</div>
										<div class="panel-body"><c:forEach var="parkingObject" items="${parkingObjects}"><c:if test = "${parkingObject.id == device.parkingObjectId}">${parkingObject.name}</c:if></c:forEach></div>
									</div>
								
								    <div class="panel panel-info">
							    		<div class="panel-heading">Numer seryjny</div>
								      	<div class="panel-body">${device.serial}</div>
								    </div>
								
								    <div class="panel panel-info">
								      	<div class="panel-heading">Typ urządzenia</div>
								      	<div class="panel-body">${device.deviceType}</div>
								    </div>
								
								    <div class="panel panel-info">
								      	<div class="panel-heading">Nazwa producenta</div>
								      	<div class="panel-body">${device.manufacturerName}</div>
								    </div>
								    
   								    <div class="panel panel-info">
								      	<div class="panel-heading">Data produkcji</div>
								      	<div class="panel-body">${device.manufacturedDate}</div>
								    </div>
								    
   								    <div class="panel panel-info">
								      	<div class="panel-heading">Data przeprowadzenia testów</div>
								      	<div class="panel-body">${device.testedDate}</div>
								    </div>
								    
   								    <div class="panel panel-info">
								      	<div class="panel-heading">Osoba odpowiedzialna za przeprowadzenie testów</div>
								      	<div class="panel-body">${device.testerName}</div>
								    </div>
								    
   								    <div class="panel panel-info">
								      	<div class="panel-heading">Informacje dodatkowe</div>
								      	<div class="panel-body">${device.additionalInfo}</div>
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