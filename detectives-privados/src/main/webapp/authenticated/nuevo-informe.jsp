<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>

<body>

  <h1>Nuevo informe</h1>

  <form action="nuevo-informe" method="post">
    <div>
      <label for="titulo">Título:</label>
      <input type="text" id="titulo" name="titulo" />
    </div>

    <div>
      <label for="descripcion">Descripción:</label>
      <input type="text" id="descripcion" name="descripcion" />
    </div>

    <div>
      <label for="contenido">Contenido:</label>
      <textarea id="contenido" name="contenido"></textarea>
    </div>

    <div>
      <label for="temaColor">Tema color:</label>
      <input type="color" id="temaColor" name="temaColor" />
    </div>

    <input type="text" hidden value="${tokenCSRF}">

    <button type="submit">Crear informe</button>
  </form>
</body>

</html>