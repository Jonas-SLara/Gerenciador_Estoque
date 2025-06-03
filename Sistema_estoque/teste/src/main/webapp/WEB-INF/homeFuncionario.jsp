<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página do funcionario</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/usuarioServlet">Sair</a>
    <h1>Sistema de estoque atacadão (funcionario)</h2>
    <h2>${pageContext.request.contextPath}</h2>
    <h2>Bem vindo, <c:out value="${sessionScope.usuario.nome}"/>!</h1>
    <h3>Confira seus dados</h3>
    <ul>
        <li><strong>Nome: </strong> <c:out value="${sessionScope.usuario.nome}"/> </li>
        <li><strong>CPF: </strong> <c:out value="${sessionScope.usuario.cpf}"/> </li>
        <li><strong>Salario: </strong> <c:out value="${sessionScope.usuario.salario}"/></li>
        <li><strong>Email: </strong> <c:out value="${sessionScope.usuario.email}"/></li>
        <li><strong>Celular: </strong> <c:out value="${sessionScope.usuario.celular}"/></li>
        <li><strong>Cargo: </strong> <c:out value="${sessionScope.funcionario.cargo}"/></li>
    </ul>

    <h3>Produtos da sua seção</h3>
    <c:choose>
        <c:when test="${empty listaProdutos}">
            <p>Nenhum produto cadastrado</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>nome:</th>
                    <th>quantidade:</th>
                    <th>valor:</th>
                    <th>id:</th>
                </tr>
                <c:forEach var="p" items="${listaProdutos}">
                    <tr>
                        <td>${p.nome}</td>
                        <td>${p.quantidade}</td>
                        <td>${p.valor}</td>
                        <td>${p.id}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>