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
    <title>Ver Funcionarios</title>
</head>
<body>
    <h2>Funcionarios cadastrados</h2>
<!-- taglib choose da core tem a função de servir como um switch case enquanto
    a taglib forEach tem a mesma função de uma estrutura de repetição-->
    <c:choose>
        <c:when test="${empty listaFuncionarios}">
            <p>Nenhum funcionário cadastrado</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Email</th
                    <th>Celular</th>
                    <th>Salário</th>
                    <th>Cargo</th>
                    <th>Opção</th>
                </tr>
                <c:forEach var="f" items="${listaFuncionarios}">
                    <tr>
                        <td>${f.id}</td>
                        <td>${f.usuario.nome}</td>
                        <td>${f.usuario.email}</td>
                        <td>${f.usuario.celular}</td>
                        <td>${f.usuario.salario}</td>
                        <td>${f.cargo}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/gerenteServlet?opcao
                            =editarFuncionario&&info=${f.id}"> Editar </a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/gerenteServlet?opcao
                            =removerFuncionario&&info=${f.id}"> Remover </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
    <br>
    <a href="${pageContext.request.contextPath}/gerenteServlet?acao=voltar">Voltar</a>

</body>
</html>
