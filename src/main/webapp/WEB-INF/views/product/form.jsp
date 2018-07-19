<%--
  Created by IntelliJ IDEA.
  User: alefhsousa
  Date: 16/07/18
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Cadastro de Livro</title>
</head>
<body>

    <c:url var="linkToSaveProduct" value="/products"/>
    <form:form action="${linkToSaveProduct}" method="post" commandName="product">
        <div>
            <label>Titulo</label>

            <form:input path="title" name="title" type="text" />
            <form:errors path="title"/>
        </div>

        <div>
            <label>Descricao</label>
            <textarea name="description" id="" cols="30" rows="10"></textarea>
            <form:errors path="description"/>
        </div>

        <div>
            <label>Autor</label>
            <form:input path="author" type="text" name="author"/>
            <form:errors path="author"/>
        </div>

        <div>
            <label>Num. de paginas</label>
            <form:input path="numberOfPages" type="text" name="numberOfPages"/>
            <form:errors path="numberOfPages"/>
        </div>

        <div>
            <label> Data de lancamento: </label>
            <form:input path="releaseDate" type="date" name="releaseDate"/>
        </div>


            <c:forEach items="${tiposLivros}" var="tipo" varStatus="status">
        <div>
                <label>${tipo} - ${status.index}</label>
                <input type="text" name="prices[${status.index}].price"/>
                <input type="hidden" value="${tipo}" name="prices[${status.index}].bookType"/>
        </div>
            </c:forEach>




        <input type="submit" value="Salvar"/>
    </form:form>
</body>
</html>
