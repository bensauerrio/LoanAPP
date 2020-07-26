<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../templates/head.jsp"></jsp:include>
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
			aria-label="Alterna navega��o">
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

	<div class="container mt-5">
		<div class="row">
			<div class="col-md-12">

				<div class="card shadow">
					<div class="card-body">
						<span class="fa-stack fa-2x"> <i
							class="fas fa-circle fa-stack-2x"></i> <i
							class="fas fa-file-signature fa-stack-1x fa-inverse"></i>
						</span>

						<div class="h3 mb-4 font-weight-normal">Listar Contratos</div>
						

						<div class="accordion text-left" id="accordion">
						
							<c:forEach 
								items="${contracts}" 
								var="contract"
								varStatus="status"> 
							
								<div class="card">
									<div class="card-header" id="heading${contract.id}">
										<h5 class="mb-0">
											<button class="btn" type="button" data-toggle="collapse"
												data-target="#collapse${contract.id}" aria-expanded="true"
												aria-controls="collapse${contract.id}">Contrato #${contract.id}</button>
										</h5>
									</div>
	
									<div id="collapse${contract.id}" class="collapse"
										aria-labelledby="headingOne" data-parent="#accordion">
										<div class="card-body">
											<p>
												<b>C�digo do contrato: </b>${contract.id}
											</p>
											<p>
												<b>Nome do coletor: </b>${contract.collector.name}
											</p>
											<p>
												<b>Total emprestado: </b>R$ <fmt:formatNumber 
																				type="number" 
																				minFractionDigits="2" 
																				maxFractionDigits="2" 
																				value="${contract.loanAmount}" />
											</p>
											<p>
												<b>Saldo: </b>R$ <fmt:formatNumber 
																				type="number" 
																				minFractionDigits="2" 
																				maxFractionDigits="2" 
																				value="${contract.loanPaymentAmountDue}" />
											</p>
											
											<a
												href="${pageContext.request.contextPath}/payment?id=${contract.id}"
												class="btn btn-sm btn btn-outline-dark">Lista de pagamentos</a>
										</div>
									</div>
								</c:forEach>
							</div>

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