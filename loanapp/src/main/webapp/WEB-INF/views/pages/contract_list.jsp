<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
							<div class="card">
								<div class="card-header" id="headingOne">
									<h5 class="mb-0">
										<button class="btn" type="button" data-toggle="collapse"
											data-target="#collapseOne" aria-expanded="true"
											aria-controls="collapseOne">Contrato #1</button>
									</h5>
								</div>

								<div id="collapseOne" class="collapse"
									aria-labelledby="headingOne" data-parent="#accordion">
									<div class="card-body">
										<p>
											<b>Código do contrato: </b>...
										</p>
										<p>
											<b>Nome do coletor: </b>...
										</p>
										<p>
											<b>Total emprestado: </b>...
										</p>
										<p>
											<b>Saldo: </b>...
										</p>
										<button class="btn btn-sm btn btn-outline-dark">Lista
											de pagamentos</button>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-header" id="headingTwo">
									<h5 class="mb-0">
										<button class="btn collapsed" type="button"
											data-toggle="collapse" data-target="#collapseTwo"
											aria-expanded="false" aria-controls="collapseTwo">
											Contrato #2</button>
									</h5>
								</div>
								<div id="collapseTwo" class="collapse"
									aria-labelledby="headingTwo" data-parent="#accordion">
									<div class="card-body">
										<p>
											<b>Código do contrato: </b>...
										</p>
										<p>
											<b>Nome do coletor: </b>...
										</p>
										<p>
											<b>Total emprestado: </b>...
										</p>
										<p>
											<b>Saldo: </b>...
										</p>
										<button class="btn btn-sm btn btn-outline-dark">Lista
											de pagamentos</button>
									</div>
								</div>
							</div>
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
	<script src="js/login.js"></script>
</body>

</html>