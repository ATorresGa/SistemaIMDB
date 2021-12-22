<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

<title>Registration Page</title>
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
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="/registration">Registration<span class="sr-only">(current)</span></a>
				</li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="mt-3 text-center">
			<form:errors path="user.*" />
		</div>
		<h1>Register!</h1>
		<form:form method="POST" action="/registration" modelAttribute="user">
			<div class="form-group">
				<form:label path="username">Username:</form:label>
				<form:input path="username" type="text" class="form-control"
					placeholder="Username" />
			</div>
			<br>
			<div class="form-group">
				<form:label path="email">Email:</form:label>
				<form:input path="email" class="form-control" placeholder="Email" />
				<small id="emailHelp" class="form-text
text-muted">We'll
					never share your email with anyone else.</small>
			</div>
			<br>
			<div class="form-group">
				<form:label path="password">Password:</form:label>
				<form:password path="password" class="form-control"
					placeholder="Password" />
					<small id="emailHelp" class="form-text
text-muted">Password must be at least 8 characters.</small>
			</div>
			<br>
			<div class="form-group">
				<form:label path="passwordConfirmation">Password
Confirmation:</form:label>
				<form:password path="passwordConfirmation" class="form-control"
					placeholder="Password Confirmation" />
			</div>
			<br>
			<input type="submit" class="btn btn-primary" value="Register!" />
		</form:form>
	</div>

		
		 <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
		
</body>
</html>