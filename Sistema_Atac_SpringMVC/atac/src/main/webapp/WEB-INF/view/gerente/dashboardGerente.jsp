<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>dashboard gerente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/btn.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/formularios.css">
    <script src="${pageContext.request.contextPath}/script/modal.js" defer></script>
</head>
<body>

    <!--nav bar-->
    <nav class="nav_menu">
        <ul>
            <li>
                <a class="current_page" href="${pageContext.request.contextPath}/dashboardGerente">
                    Home!
                </a>
            </li>

            <li>
                <!-- Passando o id do gerente através da sessão salva para o GET-->
                <a href="${pageContext.request.contextPath}/gerente/funcionarios/${gerenteLogado.id}">
                    Funcionários
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/gerente/produtos/${gerenteLogado.id}">
                    Estoque
                </a>
            </li>

            <li class="perfil" onclick="abrirModalPerfil()">
                <img src="${pageContext.request.contextPath}/img/icon-perfil.png">
                <button>
                    <c:out value="${sessionScope.gerenteLogado.usuario.nome}"/>!
                </button>
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

    <div class="overlay"></div>
    <!--Modal para o perfil-->
    <div class="modalPerfil">
        <button class="close-btn" onclick="fecharModalPerfil()">
            <img src="${pageContext.request.contextPath}/img/close.png">
        </button>
        <h2>Olá ${sessionScope.gerenteLogado.usuario.nome}</h2>
        <p>Bonificação Atual: ${gerenteLogado.bonificacao}</p>
        <p>Salário Atual: ${gerenteLogado.usuario.salario}</p>
        <p>Edite o seu perfil como preferir</p>

        <form method="POST" 
        action="${pageContext.request.contextPath}/gerente/perfil/editar"
        class="form-modal">
            <div class="label">
                <label for="perfil_nome">* Nome: </label>
                <input id="perfil_nome" type="text" value="${gerenteLogado.usuario.nome}" name="nome">
            </div>
            <div class="label">
                <label for="perfil_email">* Email: </label>
                <input id="perfil_email" type="text" value="${gerenteLogado.usuario.email}" name="email">
            </div>
            <div class="label">
                <label for="perfil_senha">* Nova Senha:</label>
                <input id="perfil_senha" type="password" value="${gerenteLogado.usuario.senha}" name="senha">  
            </div>
            <div class="label">
                <label for="perfil_celular">* Celular: </label>
                <input id="perfil_celular" type="text" value="${gerenteLogado.usuario.celular}" name="celular">
            </div>
            <input type="hidden" value="${gerenteLogado.cpfUsuario}" name="cpf">
            <input type="hidden" value="${gerenteLogado.usuario.salario}" name="salario">
            <div class="btn-form">
                <button class="btn" id="ok" type="submit">
                    CONCLUIR
                </button>
            </div>
        </form>
    </div>
</body>
</html>