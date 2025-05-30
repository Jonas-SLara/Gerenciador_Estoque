<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página do cadastro do usuário</title>
</head>
<body>
    <h2>Formulário de Cadastro de Usuario</h1>
    <form method="post" action="<%=request.getContextPath()%>/usuarioServlet">
        <label for="nome">Nome:   </label>
        <input id="nome" type="text" placeholder="digite seu nome">
        <label for="email">Email:  </label>
        <input id="email" type="text" placeholder="seuEmail@gmail.com">
        <label for="celular">Celular:</label>
        <input id="celular" type="text" placeholder="(dd) 99191-1507">
        <label for="senha">Senha:  </label>
        <input id="senha" type="password" placeholder="digite uma senha forte">
        <label for="cpf">CPF:    </label>
        <input id="cpf" type="text" placeholder="xxx.xxx.xxx-xx">
        <input type="submit" name="op" value="cadastrar">
    </form>
</body>
</html>