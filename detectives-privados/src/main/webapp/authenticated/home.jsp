<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Document</title>
    </head>

    <body>
      <h1>Bienvenido ${usuario.nombre}!</h1>
      <ul>
        <li>
          <a href="nuevo-informe">Crea un informe</a>
          <a href="informes">Informes</a>
          <a href="logout">Logout</a>
        </li>
      </ul>
    </body>

    </html>