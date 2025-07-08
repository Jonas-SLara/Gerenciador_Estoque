<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/formularios.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/btn.css">
    <script src="${pageContext.request.contextPath}/script/modal.js" defer></script>
    <title>Funcionários</title>
</head>
<body>

    <!--nav bar-->
    <nav class="nav_menu">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/dashboardGerente">
                    Home
                </a>
            </li>
            <li>
                <!-- Passando o id do gerente através da sessão salva para o GET-->
                <a class="current_page" href="${pageContext.request.contextPath}/gerente/funcionarios/${gerenteLogado.id}">
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
    <div class="overlay"></div>
    <main>
        <h2 class="title">Seus Funcionários</h2>

        <div class="main-view">
             <section class="side-bar">
                <h2>Filtros</h2>

                <div class="filtro">
                    <label for="nome">Pesquisar por Nome: </label>
                    <input type="text" id="nome">
                    <button class="search">Proc</button>
                </div>

                <div class="filtro">
                    <label for="valor_maior">Salário Maior que: </label>
                    <input type="number" id="valor_maior">
                    <button class="search">Proc</button>
                </div>

                 <div class="filtro">
                    <label for="valor_menor">Salário Menor que: </label>
                    <input type="number" id="valor_menor">
                    <button class="search">Proc</button>
                </div>
            </section>
            <!--tabela de produtos cadastrados-->
            <section class="view">
            <div class="adicionar">
                <button class="add-btn" onclick="abrirModal()">
                    CADASTRAR FUNCIONÁRIO
                    <img src="${pageContext.request.contextPath}/img/add.png" width="24px" height="24px">
                </button>

                <!--Criar um modal estilo pop up para cadastrar um novo funcionario-->
                <div class="modal">
                    <button class="close-btn" onclick="fecharModal()">
                            <img src="${pageContext.request.contextPath}/img/close.png">
                    </button>
                </div>
            </div>
                <c:if test="${not empty funcionarios}">
                    <c:forEach var="f" items="${funcionarios}">
                    <div class="item">
                        <p>Nome: ${f.usuario.nome}</p>
                        <p>Salário: ${f.usuario.salario}</p>
                        <p>Cargo: ${f.cargo}</p>
                        <p>Email: ${f.usuario.email}</p>
                        <a href="" class="excluir">
                            <img src="${pageContext.request.contextPath}/img/lixeira.png" alt="excluir-icon">
                        </a>
                        <a href="" class="editar">
                            <img src="${pageContext.request.contextPath}/img/edit.png" alt="editar-icon">
                        </a>
                    </div>
                    </c:forEach>
                </c:if>  
            </section>  
        </div>
    </main>

    <!--Modal para o perfil-->
    <div class="modalPerfil">
        <button class="close-btn" onclick="fecharModalPerfil()">
            <img src="${pageContext.request.contextPath}/img/close.png">
        </button>
        <h2>Olá ${sessionScope.gerenteLogado.usuario.nome}</h2>
    </div>
</body>
</html>
