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
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#"> <span class="fa-stack fa-2x">
				<i class="fas fa-circle fa-stack-2x"></i> <i
				class="fas fa-hand-holding-usd fa-stack-1x fa-inverse"></i>
		</span> LoanAPP
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#conteudoNavbarSuportado"
			aria-controls="conteudoNavbarSuportado" aria-expanded="false"
			aria-label="Alterna navegação">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="conteudoNavbarSuportado">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item ml-3"><a class="btn btn-sm text-left">Voltar
						<i class="fas fa-arrow-circle-left"></i>
				</a></li>
			</ul>
			<a class="btn btn-sm">Sair <i class="fas fa-sign-out-alt"></i></a>

		</div>
	</nav>

	<div class="container center-payment">

		<div class="row">
			<div class="col-md">
				<form class="form-signin"
					action="${pageContext.request.contextPath}/payment"
					modelAttribute="paymentForm" method="post">

					<div class="card shadow" style="width: 18rem;">
						<div class="card-body">
							<span class="fa-stack fa-2x"> <i
								class="fas fa-circle fa-stack-2x"></i> <i
								class="fas fa-donate fa-stack-1x fa-inverse"></i>
							</span>

							<div class="h3 mb-4 font-weight-normal">Pagamento</div>

								<div class="text-left h6 mb-3 font-weight-normal">Código do Contrato</div>
							<label for="inputCustomerId" class="sr-only">Código do Contrato</label> 
							<select class="custom-select mb-3 form-control" name="customerId"> 
							<option selected value=0>Selecione o contrato</option>
								
								<c:forEach 
									items="${contracts}" 
									var="contract"
									varStatus="status">
									<option value="${contract.id}">${contract.id}</option>
								</c:forEach>
							</select>
							<div class="text-left h6 mb-3 font-weight-normal">Valor do
								Capital Pago</div>
							<label for="inputCapitalPaid" class="sr-only">Valor de
								Capital</label> <input type="number" id="inputCapitalPaid"
								value="${payment.capitalInd}" min="0" step="any"
								class="mb-3 form-control" placeholder="00.00" required>

							<div class="text-left h6 mb-3 font-weight-normal">Valor do
								Juros Pago</div>
							<label for="inputInterestPaid" class="sr-only">Valor de
								Juros</label> <input type="number" id="inputInterestPaid" 
								value="${payment.interestInd}" min="0" step="any"
								class="mb-3 form-control" placeholder="00.00" required>


							<button class="btn btn-sm btn-outline-dark btn-block mt-3"
								type="submit">Confirmar</button>

							<label id="alertMessage" class="sr-only">${message}</label>
						</div>
					</div>
				</form>
			</div>
		</div>
		<nav class="navbar fixed-bottom navbar-light bg-light">
			<div class="row">
				<div class="col-md">
					<small class="mt-5 mb-3 text-muted">&copy; LoanAPP 2020</small>
				</div>
			</div>
		</nav>
	</div>

	<script src="component/alert/alert.component.js"></script>
</body>

</html>