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
    <title>Ver Produtos</title>
</head>
<body>
    <h2>Produtos cadastrados</h2>
    <a href="${pageContext.request.contextPath}/gerenteServlet?acao=voltar">Voltar</a>
<!-- taglib choose da core tem a função de servir como um switch case enquanto
    a taglib forEach tem a mesma função de uma estrutura de repetição-->
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
                    <th>ação:</th>
                </tr>
                <c:forEach var="p" items="${listaProdutos}">
                    <tr>
                        <td>${p.nome}</td>
                        <td>${p.quantidade}</td>
                        <td>${p.valor}</td>
                        <td>${p.id}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/gerenteServlet?acao
                            =editarProduto&&info=${p.id}">Editar</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/gerenteServlet?acao
                            =excluirProduto&&info=${p.id}">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
    <br>

    <a href="${pageContext.request.contextPath}/logoutServlet">Sair</a>
</body>
</html>