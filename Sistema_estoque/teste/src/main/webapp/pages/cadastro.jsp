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
    <h2>Formulário de Cadastro de Usuario</h2>
    <a href="<%=request.getContextPath()%>/index.jsp">Voltar</a>
    <form method="post" action="<%=request.getContextPath()%>/usuarioServlet">
        <label for="nome">Nome:   </label>
        <input id="nome" name="nome" type="text" placeholder="digite seu nome"><br>
        <label for="email">Email:  </label>
        <input id="email" name="email" type="text" placeholder="seuEmail@gmail.com"><br>
        <label for="celular">Celular:</label>
        <input id="celular" name="celular" type="text" placeholder="(dd) 99191-1507"><br>
        <label for="senha">Senha:  </label>
        <input id="senha" name="senha" type="password" placeholder="digite uma senha forte"><br>
        <label for="cpf">CPF:    </label>
        <input id="cpf" name="cpf" type="text" placeholder="xxx.xxx.xxx-xx"><br>

        <button type="submit" value="cadastrar" name="op">Cadastre-se</button>
        <button type="reset">Reset</button>
    </form>
    <c:if test="${not empty msg}">
            <h3>${msg}</h3>
    </c:if>
</body>
</html>