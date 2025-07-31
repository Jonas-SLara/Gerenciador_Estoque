<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Login</title>
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
              <a href="${pageContext.request.contextPath}/">Atac</a>
            </li>
            <li>
              <a class="current_page"  href="${pageContext.request.contextPath}/login">Login</a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/cadastro">Cadastro</a>
            </li>
        </ul>
    </nav>

    <main class="content_layout">
        <h2 class="title">Login do Usuario</h2>
        <c:if test = "${not empty erro}">
            <p style="color : rgb(180, 0, 0);">${erro}</p>
        </c:if>
        <!--formulario de login-->
        <form method="POST" action="login" class="form-main">
            <p class="aviso-form">
              todos os campos * são obrigatórios
            </p>
            <div class="label">
              <label for="cpf">CPF:   </label>
              <input id="cpf" name="cpf" type="text" placeholder="*" required>
            </div>
            <div class="label">
              <label for="senha">Senha: </label>
              <input id="senha" name="senha" type="password" placeholder="*" required><br>
            </div>
            <div class="btn-form">
              <button class="btn" id="ok" type="submit" name="op" value="login">LOGIN</button>
            </div>
        </form>
    </main>

    <!--Pop up de dados invalidos senha ou cpf incorretos-->
    <c:if test="${not empty err1}">
      <div class="overlay" style="display: block;"></div>

      <div class="modal2" style="display: block;">
        <button class="close-btn" onclick="fecharModal2()">
            <img src="${pageContext.request.contextPath}/img/close.png">
        </button>
        <h2 style="text-align: center; margin-top: 30px;">${err1}</h2>
      </div>

    </c:if>

    <!--pop up de usuario ainda nao cadastrado como funcionario, ou gerente-->
    <c:if test="${not empty err2}">
      <div class="overlay" style="display: block;"></div>

      <div class="modal2" style="display: block;">
        <button class="close-btn" onclick="fecharModal2()">
          <img src="${pageContext.request.contextPath}/img/close.png">
        </button>
        <h2 style="text-align: center; margin-top: 30px">${err2}</h2>
      </div>
      
    </c:if>
</body>
</html>