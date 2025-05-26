<%@ page isErrorPage="true" %>
<html>
<head><title>Erro na Aplicação</title></head>
<body>
  <h1>Ocorreu um erro inesperado</h1>
  <p><strong>Mensagem:</strong> <%= exception != null ? exception.getMessage() : "Erro desconhecido" %></p>
  <p><strong>Tipo de erro:</strong> <%= exception != null ? exception.getClass().getName() : "N/A" %></p>
</body>
</html>

