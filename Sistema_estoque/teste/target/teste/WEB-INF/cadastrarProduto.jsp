<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if (session.getAttribute("gerente") == null) {
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Página de cadastro Produto</title>
</head>
<body>
  <a href="${pageContext.request.contextPath}/gerenteServlet?acao=voltar">Voltar</a>
  <h2>Cadastrar Produtos</h2>

  <!--Servlet gerenteServlet recebe o parametro 'acao' para saber qual ação fazer-->

  <form method="post" action="${pageContext.request.contextPath}/gerenteServlet">
    <label for="nome">Nome:       |</label>
    <input id="nome" type="text" name="nome">
    <br>
    <label for="quantidade">Quantidade: |</label>
    <input id="quantidade" type="number" name="quantidade">
    <br>
    <label for="valor">Valor:      |</label>
    <input id="valor" name="valor" type="number" step="0.01" value="0.00">
    <br>
    <input type="submit" name="acao" value="cadastrarProduto">
    
  </form>

  <c:if test="${not empty msg}">
    <h3>${msg}</h3>
  </c:if>

</body>
</html>
