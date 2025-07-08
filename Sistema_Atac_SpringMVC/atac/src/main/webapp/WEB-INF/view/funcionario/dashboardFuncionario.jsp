<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>dashboard funcionário</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
</head>
<body>

     <!--nav bar-->
    <nav class="nav_menu">
        <ul>
            <li>
                <a class="current_page" href="${pageContext.request.contextPath}/dashboardGerente">
                    Home
                </a>
            </li>

            <li class="perfil">
                <a href="${pageContext.request.contextPath}/perfil">
                    <c:out value="${sessionScope.funcionarioLogado.usuario.nome}"/>!
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/listarProdutos">
                    Estoque
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/logout">
                    Sair
                </a>
            </li>
        </ul>
    </nav>

    <main>
        <section class="side-bar">

        </section>
        <section class="graph">

        </section>
    </main>
</body>
</html>