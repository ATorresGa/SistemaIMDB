<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

<title>Show detail</title>
<style>
.navbar{
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
	<div class="container mt-3">
		<h1>Title: ${show.showTitle}</h1>
		<h2>Network: ${show.showNetwork}</h2>
		<h2 class="font-weight-bold">Users who rated this show</h2>
		<table class="table table-dark">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Rating</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${showRatings}">
					<tr>
						<td scope="row">${row.user.username}</td>
						<td>${row.rating}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${currentUser.id == creatorShow.id }">
			<a href="/shows/${show.id}/edit" class="btn
btn-info">Edit</a>
		</c:if>
		<br /> <br />
		<form:form method="POST" action="/shows/${show.id}/add"
			modelAttribute="addRating">
			<form:hidden path="user" value="${currentUser.id}" />
			<form:hidden path="shows" value="${show.id}" />
			<div class="form-inline">
				<form:label path="rating">Leave a rating: </form:label>
				<div class="mx-3">
					<form:input type="number" min="1" max="5" path="rating"
						class="form-control" />
					<form:errors path="rating" />
				</div>
				<br>
				<div class="">
					<input type="submit" value="Rate!" class="btn btn-primary" />
				</div>
			</div>
		</form:form>
		<div class="mt-5 pb-5">
			<a href="/shows" class="btn btn-dark">Go back</a>
		</div>
	</div>

		
		 <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
		
</body>
</html>
