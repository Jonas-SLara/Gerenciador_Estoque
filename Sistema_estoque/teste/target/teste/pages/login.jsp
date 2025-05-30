<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Login</title>
</head>
<body>
    <a href="../index.jsp">Voltar</a>
    <h2>Formulário</h2>

    <c:if test = "${not empty erro}">
        <p style="color : rgb(180, 0, 0);">${erro}</p>
    </c:if>

    <form method="POST" action="<%=request.getContextPath()%>/loginServlet">
        <label for="cpf">CPF:   </label>
        <input id="cpf" name="cpf" type="text" placeholder="xxx.xxx.xxx-xx"><br>
        <label for="senha">Senha: </label>
        <input id="senha" name="senha" type="password" placeholder="sua senha"><br>
        <% out.println("todos os campos são obrigatórios"); %>
        <input type="submit" name="op" value="login"><br>
    </form>
</body>
</html>