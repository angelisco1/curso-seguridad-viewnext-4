<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
		</head>

		<body>

			<h1>Informes que coinciden con la busqueda: ${busqueda}</h1>

			<ul>
				<li>
					<a href="nuevo-informe.html">Crea un informe</a>
					<a href="informes">Informes</a>
					<a href="logout">Logout</a>
				</li>
			</ul>


			<form method="GET" action="buscar-informes">
				<div>
					<label for="busqueda">Buscar por:</label>
					<input type="text" id="busqueda" name="busqueda">
				</div>
				<button type="submit">Buscar</button>
			</form>

			<c:choose>
				<c:when test="${informes.size() > 0}">
					<c:forEach items="${informes}" var="informe">
						<div>
							<h2>${informe.titulo}</h2>
							<p>${informe.descripcion}</p>
							<a href="informe?id=${informe.id}">Ver informe</a>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>No hay ningún informe. <a href="nuevo-informe.html">Crea uno nuevo.</a></p>
				</c:otherwise>
			</c:choose>

		</body>

		</html>