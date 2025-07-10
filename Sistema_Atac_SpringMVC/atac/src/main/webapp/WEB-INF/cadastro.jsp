<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página do cadastro do usuário</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/formularios.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/btn.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
    <script src="${pageContext.request.contextPath}/script/modal.js" defer></script>
</head>

<body>

   <nav class="nav_menu">
        <ul>
            <li><img src="" class="logo">
              <a  href="${pageContext.request.contextPath}/">Atac</a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/login">Login</a>
            </li>
            <li>
              <a class="current_page"  href="${pageContext.request.contextPath}/cadastro">Cadastro</a>
            </li>
        </ul>
    </nav>

    <main class="content_layout">

        <h2>Cadastro do Usuário</h2>
        
        <form method="post" action="${pageContext.request.contextPath}/cadastroUsuario" class="form-main">
          <p style="color: rgb(0, 0, 0); font-size: 0.8rem;">todos os campos * são obrigatórios</p>
          <div class="label">
            <label for="nome">Nome:</label>
            <input id="nome" name="nome" type="text" placeholder="*" required><br>
          </div>
            
          <div class="label">
            <label for="email">Email:</label>
            <input id="email" name="email" type="text" placeholder="*" required><br>
          </div>

          <div class="label">
            <label for="celular">Celular:</label>
            <input id="celular" name="celular" type="text" placeholder="*" required><br>
          </div>
            
          <div class="label">
            <label for="senha">Senha:  </label>
            <input id="senha" name="senha" type="password" placeholder="*" required><br>
          </div>

          <div class="label">
            <label for="cpf">CPF:    </label>
            <input id="cpf" name="cpf" type="text" placeholder="*" required><br>
          </div>

          <div class="btn-form">
            <button class="btn" id="ok" type="submit">Cadastre-se</button>
            <button class="btn warning" type="reset">Reset</button>
          </div>
        </form>
    </main>

    <!-- Pop Up de mensagegem de sucesso-->
    <c:if test="${not empty msg}">
      <div class="overlay" style="display: block;"></div>

      <div class="modal2" style="display: block;">
        <button class="close-btn" onclick="fecharModal2()">
            <img src="${pageContext.request.contextPath}/img/close.png">
        </button>
        <h2>Usuario(a) Cadastrado(a)!</h2>
      </div>  
    </c:if>

    <!--Pop Up de mensagem de erro-->
    <c:if test="${not empty err}">
      <div class="overlay" style="display: block;"></div>

      <div class="modal2" style="display: block;">
        <button class="close-btn" onclick="fecharModal2()">
            <img src="${pageContext.request.contextPath}/img/close.png">
        </button>
        <h2>Erro ao cadastrar</h2>
        <p>${err}</p>
      </div>  
    </c:if>
</body>
</html>