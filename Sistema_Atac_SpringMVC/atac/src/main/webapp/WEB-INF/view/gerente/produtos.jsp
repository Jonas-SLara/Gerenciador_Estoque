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
    <title>Seu Estoque</title>
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
                <a href="${pageContext.request.contextPath}/gerente/funcionarios/${gerenteLogado.id}">
                    Funcionários
                </a>
            </li>
            <li>
                <a class="current_page" 
                    href="${pageContext.request.contextPath}/gerente/produtos/${gerenteLogado.id}">
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

                <div class="adicionar">
                    <button class="add-btn" onclick="abrirModal()">
                        ADICIONAR PRODUTO
                        <img src="${pageContext.request.contextPath}/img/add.png"
                            width="24px" height="24px">
                    </button>

                    <!-- aqui vai surgir um formulario dentro de um modal para cadastro-->
                    <div class="overlay"></div>
                    <div class="modal">
                        <button class="close-btn" onclick="fecharModal()">
                            <img src="${pageContext.request.contextPath}/img/close.png">
                        </button>
                        <h2>Cadastro de Produto</h2>
                        <p>de uma nova entrada de produto no estoque</p>
                        <form method="POST" class="form-modal"
                            action="${pageContext.request.contextPath}/gerente/produtos/${gerenteLogado.id}/cadastrar">

                            <div class="label">
                                <label for="name">Nome:</label>
                                <input id="name" name="nome" type="text" required>
                            </div>

                             <div class="label">
                                <label for="valor">Valor:</label>
                                <input id="valor" name="valor" type="number" step="0.01" required>
                            </div>

                            <div class="label">
                                <label for="quantidade">Quantidade Inicial:</label>
                                <input id="quantidade" name="quantidade" type="number" required>
                            </div>

                            <div class="btn-form">
                                <button class="btn" id="ok" type="submit">
                                    CONCLUIR
                                </button>
                            </div>
                        </form>
                    </div>
                </div>

                <c:if test="${not empty produtos}">
                <c:forEach var="p" items="${produtos}">
                <div class="item">
                    <p>Nome: ${p.nome}</p>
                    <p>QTD: ${p.quantidade}</p>
                    <p>Valor: ${p.valor}</p>
                    <button class="excluir" 
                        data-id="${p.id}"
                        data-idGerente="${p.idGerente}"
                        data-url="${pageContext.request.contextPath}/gerente/produtos/${p.idGerente}/excluir/${p.id}"
                        onclick="abrirModal2(this)">
                        <img src="${pageContext.request.contextPath}/img/lixeira.png" alt="excluir-icon">
                    </button>
                    <button class="editar" 
                        data-id="${p.id}"
                        data-nome="${p.nome}"
                        data-valor="${p.valor}"
                        data-quantidade="${p.quantidade}"
                        onclick="abrirModal3(this)">
                        <img src="${pageContext.request.contextPath}/img/edit.png" alt="editar-icon">
                    </button>
                </div>
                </c:forEach>
                </c:if>  
       
         </section>  
        </div>
    </main>

    <!--Modal de alerta para o excluir produto-->
    <div class="modal2">
        <button class="close-btn" onclick="fecharModal2()">
            <img src="${pageContext.request.contextPath}/img/close.png">
        </button>
        <h2>Excluir Produto do Estoque!</h2>
        <p style="margin: 10px 0 15px 0;">Tem certeza?</p>
        <!--Link que passa o id do produto a ser excluido-->
        <a class="btn warning" id="btn-excluir" href="">
            Excluir
        </a>
    </div>

    <!--Modal para o editar Produto-->
    <div class="modal3">
        <button class="close-btn" onclick="fecharModal3()">
            <img src="${pageContext.request.contextPath}/img/close.png">
        </button>
        <h2>Editar Produto do Estoque</h2>
        <p>* são valores atuais, edite ou deixe como está</p>
        <form method="POST" class="form-modal" id="form-edit"
        action="${pageContext.request.contextPath}/gerente/produtos/${gerenteLogado.id}/editar">

            <div class="label">
                <label for="name-edit">* Nome:</label>
                <input id="name-edit" name="nome" type="text" value="">
            </div>

            <div class="label">
                <label for="valor-edit">* Valor:</label>
                <input id="valor-edit" name="valor" type="number" step="0.01" value="">
            </div>

            <div class="label">
                <label for="quantidade-edit">* Quantidade:</label>
                <input id="quantidade-edit" name="quantidade" type="number" value="">
            </div>

            <input name="id" id="codigo" type="hidden" value="">

            <div class="btn-form">
                <button class="btn" id="ok" type="submit">
                    CONCLUIR
                </button>
            </div>
        </form>
    </div>

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