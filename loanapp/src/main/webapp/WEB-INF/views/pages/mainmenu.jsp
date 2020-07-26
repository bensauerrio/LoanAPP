<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../templates/head.jsp"></jsp:include>
</head>
<body class="bg-dark">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#"> <span class="fa-stack fa-2x">
				<i class="fas fa-circle fa-stack-2x"></i> <i
				class="fas fa-hand-holding-usd fa-stack-1x fa-inverse"></i>
		</span> LoanAPP</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#conteudoNavbarSuportado"
			aria-controls="conteudoNavbarSuportado" aria-expanded="false"
			aria-label="Alterna navega��o">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="conteudoNavbarSuportado">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item ml-3">
					<!-- <a href="#" class="btn btn-sm text-left" onclick="history.back();">Voltar
						<i class="fas fa-arrow-circle-left"></i>
					</a> -->
				</li>
			</ul>
			<a href="logout" class="btn btn-sm">Sair <i class="fas fa-sign-out-alt"></i></a>
			
		</div>
	</nav>

	<div class="container">

		<div class="row mt-5 justify-content-md-center">

			<c:forEach items="${sessionFunctionalities}" var="functionality"
				varStatus="status">

				<div class="col-md-auto ${functionality.hidden ? 'd-none' : ''}">
					<div class="card shadow" style="width: 18rem;">
						<div class="card-body text-center">
							<span class="fa-stack fa-2x"> <i
								class="fas fa-circle fa-stack-2x"></i> <i
								class="fas fa-search-dollar fa-stack-1x fa-inverse"></i>
							</span>

							<p class="card-text mt-3">${functionality.name}</p>
						</div>

						<div class="card-body text-center bg-light">
							<c:if test="${functionality.enabled}">
								<a
									href="${pageContext.request.contextPath}/mainmenu?path=${functionality.route}"
									class="btn btn-sm btn-outline-dark card-link">Acessar</a>
							</c:if>

							<c:if test="${!functionality.enabled}">
								<a href="#" class="btn btn-sm btn-outline-dark card-link">Acessar</a>
							</c:if>


						</div>
					</div>
				</div>

			</c:forEach>

		</div>


		<nav class="navbar fixed-bottom navbar-light bg-light">
			<div class="row">
				<div class="col-md">
					<small class="mt-5 mb-3 text-muted">&copy; LoanAPP 2020</small>
				</div>
			</div>
		</nav>
	</div>


	<label id="alertMessage" class="sr-only">${message}</label>
	<script src="component/alert/alert.component.js"></script>


</body>
</html>