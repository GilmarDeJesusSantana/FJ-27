<%--
  Created by IntelliJ IDEA.
  User: alefhsousa
  Date: 17/07/18
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Listagem de Produtos</title>
</head>
<body>
    <h1> Listagem de Livros </h1>

    ${success}
    <table>
        <th>Titulo</th>
        <th>Autor</th>
        <th>Num Paginas</th>
        <th>Tipos</th>

        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.title}</td>
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
</html>
