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
	<div class="container center-contract">
		<div class="row">
			<div class="col-md">
				<form class="form-signin"
					action="${pageContext.request.contextPath}/contract"
					modelAttribute="contractForm" method="post">

					<div class="card shadow" style="width: 18rem;">
						<div class="card-body">
							<span class="fa-stack fa-2x"> <i
								class="fas fa-circle fa-stack-2x"></i> <i
								class="fas fa-file-signature fa-stack-1x fa-inverse"></i>
							</span>

							<div class="h3 mb-4 font-weight-normal">Contrato</div>


							<div class="text-left h6 mb-3 font-weight-normal">Código do
								Cliente</div>
							<label for="inputCustomerId" class="sr-only">Código do
								Cliente</label>
							<form:select path="customers" class="mb-3 form-control">

								<c:forEach items="${customers}" var="customer"
									varStatus="status">
									<option value="${customer.id}">${customer.id}-
										${customer.name}</option>
								</c:forEach>
							</form:select>

							<div class="text-left h6 mb-3 font-weight-normal">Taxa de
								Juros</div>
							<label for="inputInterest" class="sr-only">Taxa de Juros</label>
							<input type="number" id="inputInterest" name="interestRate"
								min="0" step="any" class="mb-3 form-control" placeholder="00.00"
								required>

							<div class="text-left h6 mb-3 font-weight-normal">Valor do
								Empréstimo</div>
							<label for="inputLoan" class="sr-only">Valor do
								Empréstimo</label> <input type="number" id="inputLoan" name="loanAmount"
								min="0" step="any" class="mb-3 form-control" placeholder="00.00"
								required>

							<div class="text-left h6 mb-3 font-weight-normal">Quantidade
								de Parcelas</div>
							<label for="inputQttInstallments" class="sr-only">Quantidade
								de Parcelas</label> <input type="number" id="inputQttInstallments"
								name="qttInstallments" min="0" class="mb-3 form-control"
								placeholder="0" required>

							<button class="btn btn-sm btn-outline-info btn-block mt-3"
								type="button" data-toggle="modal" data-target="#simularLoanApp">Simular</button>


							<button class="btn btn-sm btn-outline-dark btn-block mt-3"
								type="submit">Submeter</button>

							<label id="alertMessage" class="sr-only">${message}</label>

						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="modal fade" id="simularLoanApp" tabindex="-1"
			role="dialog" aria-labelledby="simularLoanApp" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-body">
						<button type="button" class="close close-dark mr-3"
							data-dismiss="modal" aria-label="Fechar">
							<span aria-hidden="true">&times;</span>
						</button>
						<span class="fa-stack fa-2x mt-4 ml-4"> <i
							class="fas fa-circle fa-stack-2x"></i> <i
							class="fas fa-clone fa-stack-1x fa-inverse"></i>
						</span>
						<div class="h4 mb-4 font-weight-normal text-center">Simulação</div>
						<table class="table table-borderless table-secondary h-100 mt-4">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Data do pagamento</th>
									<th scope="col">Parcela</th>
									<th scope="col">Capital</th>
									<th scope="col">Juros</th>
									<th scope="col">Total</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">1</th>
									<td>...</td>
									<td>...</td>
									<td>...</td>
									<td>...</td>
									<td>...</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
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
	<script src="js/login.js"></script>
</body>

</html>