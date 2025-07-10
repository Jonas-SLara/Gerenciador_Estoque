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
    <!--fundo escuro quando abrir um modal-->
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
            </div>
                <c:if test="${not empty funcionarios}">
                    <c:forEach var="f" items="${funcionarios}">
                    <div class="item">
                        <p>Nome: ${f.usuario.nome}</p>
                        <p>Salário: ${f.usuario.salario}</p>
                        <p>Cargo: ${f.cargo}</p>
                        <p>Email: ${f.usuario.email}</p>
                        <button class="excluir"
                        data-id="${f.id}"
                        data-idGerente="${f.idGerente}"
                        data-url="${pageContext.request.contextPath}/gerente/funcionarios/${f.idGerente}/excluir/${f.id}"
                        onclick="abrirModal2(this)">
                            <img src="${pageContext.request.contextPath}/img/lixeira.png" alt="excluir-icon">
                        </button>

                        <button class="editar"
                        data-id="${f.id}"
                        data-cpf="${f.usuario.cpf}"
                        data-salario="${f.usuario.salario}"
                        data-cargo="${f.cargo}"
                        onclick="abrirModal4(this)">
                            <img src="${pageContext.request.contextPath}/img/edit.png" alt="editar-icon">
                        </button>
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

      <!--Criar um modal estilo pop up para cadastrar um novo funcionario-->
    <div class="modal">
        <button class="close-btn" onclick="fecharModal()">
            <img src="${pageContext.request.contextPath}/img/close.png">
        </button>
        <h2>Cadastro de novo Funcionario</h2>
        <p>Busque o usuário (CPF)</p>
        
        <!-- O gerente busca um usuario primeiro, envia a requisição por post, retornara com os dados
         buscados em um pop up na tela com dados do usuario buscado e um formulario para preencher os dados
         ou uma opção de só cancelar-->

        <form method="POST" action="${pageContext.request.contextPath}/gerente/funcionarios/buscar" class="form-modal">
            <div class="label">
                <label for="cpf-usuario">CPF:</label>
                <input type="text" name="cpf" required>
            </div>
            <input type="hidden" name="ig" value="${sessionScope.gerenteLogado.id}">
            <div class="btn-form">
                <button type="submit" class="btn" id="ok">Buscar</button>
            </div>
        </form>
    </div>

    <!-- Modal para confirmar cadastro de novo funcionario e dar a ele o cargo e salario-->
    <c:if test="${not empty usuarioBuscado}">
        <div class="overlay2" style="display: block;"></div>
        <div class="modal5" style="display: block;">
            <button class="close-btn" onclick="fecharModal5()">
                <img src="${pageContext.request.contextPath}/img/close.png">
            </button>
            <h2>Usuario Buscado com êxito</h2>
            <h3>Confira os dados abaixo</h3>
            <p>Nome: ${usuarioBuscado.nome}</p>
            <p>Email: ${usuarioBuscado.email}</p>
            <p>CPF: ${usuarioBuscado.cpf}</p>

            <form method="POST" 
            action="${pageContext.request.contextPath}/gerente/funcionarios/cadastrar"
            class="form-modal">
                <div class="label">
                    <label for="cargo-novo">Cargo:</label>
                    <input id="cargo-novo" type="text" name="cargo" required>
                </div>
                <div class="label">
                    <label for="salario-novo">Salário</label>
                    <input id="salario-novo" type="number" name="salario" required>
                </div>
                <!-- tem que passar o cpf pois é chave estrangeira para buscar o usuario dele depois-->
                <input type="hidden" name="cpfUsuario" value="${usuarioBuscado.cpf}">
                <input type="hidden" name="idGerente" value="${gerenteLogado.id}">
                <div class="btn-form">
                    <button type="submit" id="ok" class="btn">CONCLUIR</button>
                </div>
            </form>
        </div>
    </c:if>

    <c:if test="${not empty usuarioErr}">
        <div class="overlay2" style="display: block;"></div>
        <div class="modal6" style="display: block;">
            <button class="close-btn" onclick="fecharModal6()">
                <img src="${pageContext.request.contextPath}/img/close.png">
            </button>
            <h2>${usuarioErr}</h2>
        </div>
    </c:if>

    <!--Modal2 de alerta para o excluir o funcionario-->
    <div class="modal2">
        <button class="close-btn" onclick="fecharModal2()">
            <img src="${pageContext.request.contextPath}/img/close.png">
        </button>
        <h2>Excluir Funcionário</h2>
        <p style="margin: 10px 0 15px 0;">Tem certeza?</p>
        <!--Link que passa o id do funcionario a ser excluido-->
        <a class="btn warning" id="btn-excluir" href="">
            Excluir
        </a>
    </div>

    <!--Modal para o editar Funcionario-->
    <div class="modal4">
        <button class="close-btn" onclick="fecharModal4()">
            <img src="${pageContext.request.contextPath}/img/close.png">
        </button>
        <h2>Editar Funcionario</h2>
        <p>* são valores atuais, edite ou deixe como está</p>
        <form method="POST" class="form-modal" id="form-edit"
        action="${pageContext.request.contextPath}/gerente/funcionarios/editar">

            <div class="label">
                <label for="salario-edit">* Salario:</label>
                <input id="salario-edit" name="salario" type="text" value="">
            </div>

            <div class="label">
                <label for="cargo-edit">* Cargo:</label>
                <input id="cargo-edit" name="cargo" type="text" value="">
            </div>

            <input name="id" id="id-edit" type="hidden" value="">
            <input name="cpfUsuario" id="cpf-edit" type="hidden" value="">
            <input name="idGerente" type="hidden" value="${gerenteLogado.id}">
            <div class="btn-form">
                <button class="btn" id="ok" type="submit">
                    CONCLUIR
                </button>
            </div>
        </form>
    </div>
</body>
</html>
