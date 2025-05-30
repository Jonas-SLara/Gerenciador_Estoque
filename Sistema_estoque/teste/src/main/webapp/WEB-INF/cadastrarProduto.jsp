<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Página de cadastro Produto</title>
</head>
<body>
  <h2>Cadastrar Produtos</h2>
  <a href="${pageContext.request.contextPath}/gerenteServlet?acao=voltar">Voltar</a>
  <form method="post" action="">
    <label for="nome">Nome:     </label>
    <input id="nome" type="text" name="nome">
    <label for="quantidade">Quantidade: </label>
    <input id="quantidade" type="number" name="quantidade">
    <label for="valor">Valor</label>
    <input id="valor" name="valor" type="number">
    <input type="submit" name="op" value="cadastrarProduto">
  </form>
  <a href="${pageContext.request.contextPath}/logoutServlet">Sair</a>
</body>
</html>
