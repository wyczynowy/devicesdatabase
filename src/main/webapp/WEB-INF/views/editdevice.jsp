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
	<title>Adding new device</title>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
	$( function() {
	  $( "#datepicker" ).datepicker();
	} );
	</script>
	<script>
	$( function() {
	  $( "#datepicker2" ).datepicker();
	} );
	</script>
  
</head>
<body>

	<div class="horizontal-center" style="width: 10%; min-width:278px; top: 20; margin: 0 auto;">
		<h1><span class="label label-default">Edycja urządzenia</span></h1>
	</div>
	
	<div style="width: 50%; margin: 0 auto; margin-bottom: 50px;">
		<div style="height: 10%; min-height: 550px; margin-top: 5%;">
			<form:form action="${pageContext.request.contextPath}/managedevices/updatefilleddeviceform?id=${device.id}" modelAttribute="device" method="post">
		
			<!-- <div class="form-group">
				 <label for="comment">Id parkingu:</label> 
				 <form:input path="parkingObjectId" id="parkingObjectId" class="form-control"></form:input>
			 </div> -->
			 
 			<div class="form-group">
			  <label for="sel1">Obiekt parkingowy:</label>
			  <select class="form-control" name="parkingObjectId" id="sel1" size="5" required>
			  <c:forEach var="parkingObject" items="${parkingObjects}">
			    <option <c:if test = "${parkingObject.id == device.parkingObjectId}">selected</c:if> value="${parkingObject.id}">${parkingObject.name}</option>
			    </c:forEach>
			  </select>
			</div>
			
			 <div class="form-group">
				 <label for="comment">Nazwa producenta:</label> 
				 <form:textarea path="manufacturerName" id="manufacturerName" class="form-control" rows="1" required="required"></form:textarea>
				 <!--<c:if test="${pageContext.request.method=='POST'}"><form:errors path="${newnote.adviceDescription}" /></c:if>-->
			 </div>
			 
 			 <div class="form-group">
			 	<label for="datepicker">Data produkcji: </label>
			 	<input name="manufacturedDate" type="text" id="datepicker" value="${convertedManufacteredDate}" class="form-control" required>
			 </div>
			 
 			 <!-- <div class="form-group">
				 <label for="comment">Data produkcji:</label> 
				 <form:textarea path="manufacturedDate" id="manufacturedDate" class="form-control" rows="1"></form:textarea>
			 </div> -->
			 
  			 <div class="form-group">
			 	<label for="datepicker2">Data przeprowadzonych testów: </label>
			 	<input name="testedDate" type="text" id="datepicker2" value="${convertedTestedDate}" class="form-control" required>
			 </div>
			 
  			 <!--<div class="form-group">
				 <label for="comment">Data przeprowadzonych testów:</label> 
				 <form:textarea path="testedDate" id="testedDate" class="form-control" rows="1"></form:textarea>
			 </div>-->
			 
   			 <div class="form-group">
				 <label for="comment">Osoba odpowiedzialna za przeprowadzenie testów:</label> 
				 <form:textarea path="testerName" id="testerName" class="form-control" rows="1" required="required"></form:textarea>
				 <!--<c:if test="${pageContext.request.method=='POST'}"><form:errors path="${newnote.adviceDescription}" /></c:if>-->
			 </div>
			 
   			 <div class="form-group">
				 <label for="comment">Informacje dodatkowe:</label> 
				 <form:textarea path="additionalInfo" id="additionalInfo" class="form-control" row="5"></form:textarea>
				 <!--<c:if test="${pageContext.request.method=='POST'}"><form:errors path="${newnote.adviceDescription}" /></c:if>-->
			 </div>
			 
 			 <!--<div class="form-group">
				 <label for="comment">Typ urządzenia:</label> 
				 <form:textarea path="deviceType" id="deviceType" class="form-control" rows="1"></form:textarea>
			 </div>-->
			 
			<div class="form-group">
			  <label for="sel2">Typ urzadzenia:</label>
			  <select class="form-control" name="deviceType" id="sel2" size="5" required>
			  <option <c:if test = "${device.deviceType == 'Server'}">selected</c:if> value="Server">Server</option>
			  <option <c:if test = "${device.deviceType == 'Hub'}">selected</c:if> value="Hub">Hub</option>
			  <option <c:if test = "${device.deviceType == 'Tablica'}">selected</c:if> value="Tablica">Tablica</option>
			  <option <c:if test = "${device.deviceType == 'Czujnik'}">selected</c:if> value="Czujnik">Czujnik</option>
			  <option <c:if test = "${device.deviceType == 'Relay'}">selected</c:if> value="Relay">Relay</option>
			  </select>
			</div>
			 
			 <div class="form-group">
				 <label for="comment">Numer seryjny urządzenia:</label> 
				 <form:textarea path="serial" id="serial" class="form-control" rows="1"></form:textarea>
				 <!--<c:if test="${pageContext.request.method=='POST'}"><form:errors path="${newnote.adviceDescription}" /></c:if>-->
			 </div>
			 			
			 <input class="btn btn-success" type="submit" value = "Zapisz" />
			 <input class="btn btn-warning" type="button" value = "Wróć" onclick="window.location.href='${pageContext.request.contextPath}/managedevices/'"/>
			</form:form>
		</div>
</div>
</body>
</html>