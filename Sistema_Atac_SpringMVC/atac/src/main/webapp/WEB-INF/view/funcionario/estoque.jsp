<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>Estoque</title>
</head>
<body>
    
    <!--nav bar-->
    <nav class="nav_menu">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/dashboardFuncionario">
                    Home
                </a>
            </li>

            <li>
                <a class="current_page"
                href="${pageContext.request.contextPath}/funcionario/estoque/${funcionarioLogado.idGerente}">
                    Estoque
                </a>
            </li>

            <li class="perfil" onclick="abrirModalPerfil()">
                <img src="${pageContext.request.contextPath}/img/icon-perfil.png">
                <button>
                    <c:out value="${sessionScope.funcionarioLogado.usuario.nome}"/>!
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
        <h2 class="title">Seu Estoque</h2>
        <div class="main-view">
            <section class="side-bar">
                <h2>Filtros</h2>

                <div class="filtro">
                    <label for="nome">Pesquisar por Nome: </label>
                    <input type="text" id="nome">
                    <button class="search">Proc</button>
                </div>

                <div class="filtro">
                    <label for="valor_maior">Valor Maior que: </label>
                    <input type="number" id="valor_maior">
                    <button class="search">Proc</button>
                </div>

                 <div class="filtro">
                    <label for="valor_menor">Valor Menor que: </label>
                    <input type="number" id="valor_menor">
                    <button class="search">Proc</button>
                </div>
            </section>

            <!--tabela de produtos cadastrados-->
            <section class="view">
                <c:if test="${not empty produtos}">
                    <c:forEach var="p" items="${produtos}">
                    <div class="item">
                        <p>Nome: ${p.nome}</p>
                        <p>QTD: ${p.quantidade}</p>
                        <p>Valor: ${p.valor}</p>
                        <button class="editar" 
                            data-id="${p.id}"
                            data-nome="${p.nome}"
                            data-valor="${p.valor}"
                            data-quantidade="${p.quantidade}"
                            onclick="abrirModal3(this)">
                            <img src="${pageContext.request.contextPath}/img/edit.png"
                                alt="editar-icon">
                        </button>
                    </div>
                    </c:forEach>
                </c:if>  
         </section>  
        </div>
    </main>

    <div class="overlay"></div>

    <!--Modal para o editar Produto-->
    <div class="modal3">
        <button class="close-btn" onclick="fecharModal3()">
            <img src="${pageContext.request.contextPath}/img/close.png">
        </button>

        <h2>Editar Produto do Estoque</h2>
        <p>* são valores atuais, edite ou deixe como está</p>

        <form method="POST" class="form-modal" id="form-edit"
        action="${pageContext.request.contextPath}/funcionario/estoque/${funcionarioLogado.idGerente}/editar">

            <div class="label">
                <label for="valor-edit">* Valor:</label>
                <input id="valor-edit" name="valor" type="number" step="0.01" value="">
            </div>

            <div class="label">
                <label for="quantidade-edit">* Quantidade:</label>
                <input id="quantidade-edit" name="quantidade" type="number" step="1" value="">
            </div>

            <input name="id" id="codigo" type="hidden" value="">
            <input name="nome" id="name-edit" type="hidden" value="">
             
            <div class="btn-form">
                <button class="btn" id="ok" type="submit">
                    CONCLUIR
                </button>
            </div>
        </form>
    </div>
</body>
</html>