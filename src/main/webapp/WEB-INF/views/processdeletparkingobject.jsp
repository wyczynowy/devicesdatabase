<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
	<title>Confirm choice</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="Shortcut icon" href="${pageContext.request.contextPath}/resources/pictures/BATI_LOGO_KULA_RED_bez_tla.ico" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div style="width: 20%; height: 100vh; min-width: 800px; margin: 0 auto;">
		<div style="height: 10%; min-height: 400px; margin-top: 20vh;">
			<div class="well well-lg" style="width: 10%; min-width: 700px; margin: 0 auto;">
				<div><h4>Potwierdź usunięcie obiektu wraz z urządzeniami powiązanymi:</h4></div>
				<div style="margin: 0 auto; text-align: center;"><h2><span class="label label-info">${parkingName}</span></h2></div>
			</div>
			<div style="width: 10%; min-width: 700px; margin: 25px auto;" class="well well-sm">
				Ilość powiązanych urządzeń: ${countRelatedDevices}
			</div>
			<div style="width: 10%; min-width: 135px; margin: 0 auto;">
				<input class="btn btn-danger" type="button" value = "Usuń" 
					onclick="window.location.href='${pageContext.request.contextPath}/manageparkings/proceeddeletparkingobject?parkingObjectId=${parkingObjectId}'"/>
				<input class="btn btn-success" type="button" value = "Anuluj" 
					onclick="window.location.href='${pageContext.request.contextPath}/manageparkings/'"/>
			</div>
		</div>
	</div>


</body>
</html>