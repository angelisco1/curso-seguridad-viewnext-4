<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
		</head>

		<body>

			<h1>Informe con id ${informe.id}</h1>

			<ul>
				<li>
					<a href="nuevo-informe">Crea un informe</a>
					<a href="informes">Informes</a>
					<a href="logout">Logout</a>
				</li>
			</ul>


			<div style="color: ${informe.temaColor}">
				<h2>${informe.titulo}</h2>
				<p>${informe.descripcion}</p>
				<p>
				<pre>${informe.contenido}</pre>
				</p>
			</div>

		</body>

		</html>