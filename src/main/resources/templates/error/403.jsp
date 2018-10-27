<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Seguridad de la aplicacion</title>
</head>
<body class="security-app">
	<div class="details">
	<h2>Seguridad de la aplicacion</h2>
		<a href="http://www.programming-free.com/2016/01/spring-security-spring-data-jpa.html" class="button green small">Tutorial</a> 
		<a href="https://github.com/priyadb/SpringSecurityJdbcApp/archive/master.zip"
			class="button red small">Download</a>
	</div>
	<div class="lc-block">		
		<div class="alert-danger">
			<h3>Usted no posee permisos para ingresar a la pagina!</h3>	
		</div>
		<form action="/logout" method="post">
			<input type="submit" class="button red big" value="Sign in as different user" /> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>		
	</div>	
</body>
</html>
