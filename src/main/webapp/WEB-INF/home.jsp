<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">

<title>Home Page</title>
<style>
.navbar {
	padding-left: 5%
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/">TV Shows</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item mr-5"><a class="nav-link" href="/shows/new">Add
						a show</a></li>
				<li class="nav-item">
					<form id="logoutForm" method="POST" action="/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <input type="submit" value="Logout"
							class="btn btn-link
text-secondary" />
					</form>
				</li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="py-3">
			<h1 class="font-weight-bold">
				Welcome,
				<c:out value="${currentUser.username}"></c:out>
			</h1>
		</div>
		<h2>TV shows</h2>
		<table class="table table-dark">
			<thead>
				<tr>
					<th scope="col">Show</th>
					<th scope="col">Network</th>
					<th scope="col">prom</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${allShows}" var="show">
					<tr>
						<td scope="row"><a href="/shows/${show.id}"
							class="text-info font-weight-bold">${show.showTitle}</a></td>
						<td>${show.showNetwork}</td>
					<!-- 	<td>
						${show.ratings}
							</td> -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"></script>

</body>
</html>