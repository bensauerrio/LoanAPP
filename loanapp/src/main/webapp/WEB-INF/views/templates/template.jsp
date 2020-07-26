<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../templates/head.jsp"></jsp:include>
<link href="css/login.css" rel="stylesheet">
</head>
<body class="text-center bg-dark">
	<div class="container">
		<div class="row justify-content-md-center">

			<label id="alertMessage" class="sr-only">${message}</label>

		</div>

		<nav class="navbar fixed-bottom navbar-light bg-light">
			<div class="row">
				<div class="col-md">
					<small class="mt-5 mb-3 text-muted">&copy; LoanAPP 2020</small>
				</div>
			</div>
		</nav>
	</div>
	</div>

	<script src="component/alert/alert.component.js"></script>
	<script src="js/login.js"></script>
</body>

</html>