<%--
  Created by IntelliJ IDEA.
  User: alefhsousa
  Date: 17/07/18
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cdc" tagdir="/WEB-INF/tags"%>

<cdc:page title="Listagem de Produtos">
	<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="user"/>
    <div>
<%--        Olá ${user.name} --%>
       <spring:message code="usuario.bemvindo" arguments="${user.name}">
       </spring:message>
    </div>
    </sec:authorize>
           


<body>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="user"/>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<!--Este erro e um bug do eclipse, a aplicacao funciona. -->
	<a href="${spring:mvcUrl('PC#form').build()}" method=post>Cadastrar Produto</a>
	</sec:authorize>
	
	<c:url var="urlToLogout" value="/logout"/>
	<a href="${urlToLogout}">Sair</a>
	
</sec:authorize>

    <h1> Listagem de Livros </h1>

    ${success}
    <table>
        <th>Titulo</th>
        <th>Autor</th>
        <th>Num Paginas</th>
        <th>Tipos</th>

        <c:forEach items="${books}" var="book">
        <c:url value="/products/${book.id}" var="linkDetalhar"></c:url>
            <tr>
                <td> <a href="${linkDetalhar}">${book.title}</a></td>
                <td>${book.author}</td>
                <td>${book.numberOfPages}</td>
                <td>
                    <c:forEach items="${book.prices}" var="price">
                        ${price.bookType}: ${price.price} -
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>


    </table>
</body>
</cdc:page>
