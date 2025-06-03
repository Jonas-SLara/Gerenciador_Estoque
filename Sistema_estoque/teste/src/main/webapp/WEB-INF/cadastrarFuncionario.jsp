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
    <title>Página de cadastro do funcionário</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/gerenteServlet?acao=voltar">Voltar</a>
    <h2>Cadastrar Funcionario</h2>
    <!--Servlet gerenteServlet recebe o parametro 'acao' para saber qual ação fazer-->
    
    <!--Primeiro vai ter a opção de buscar o usuario pelo cpf para o gerente ver se é
    este mesmo que deve adicionar-->
    <h3>1º Passo busque seu usuario pelo cpf e confira os dados dele</h3>
    <p>o sistema não deixa cadastrar um funcionário já cadastrado</p>

    <form method="post" action="<%=request.getContextPath()%>/usuarioServlet">
        <label for="cpf">CPF: </label>
        <input type="text" name="cpf" id="cpf" required>
        <input type="submit" name="op" value="buscar">
    </form>

    <c:if test="${not empty erro}">
        <h3>${erro}</h3>
    </c:if>
    <c:if test="${not empty usuarioEncontrado}">
        <h3>usuario encontrado: ${usuarioEncontrado.nome}  ${usuarioEncontrado.email}</h3>
    </c:if>

    <!--caso não receba nenhum erro ao buscar o usuario prossiga-->
    <c:if test="${empty erro and not empty usuarioEncontrado}">
        <h3>2º Passo cadastre o usuario como seu funcionário</h3>

        <form action="${pageContext.request.contextPath}/gerenteServlet" method="post">
            <input id="cpf_usuario" name="cpf_usuario" value="${usuarioEncontrado.cpf}" type="hidden">
            <br>
            <label for="cargo">Cargo:</label>
            <input id="cargo" name="cargo" type="text" required>
            <br>
            <label for="salario">Salario:</label>
            <input id="salario" name="salario" type="number" step="0.01" value="0.00" required>
            <br>
            <button type="submit" name="acao" value="cadastrarFuncionario">Cadastrar</button>
        </form>
    </c:if>

    <c:if test="${not empty msg}">
        <h3>${msg}</h3>
    </c:if>

</body>
</html>
