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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="cdc" tagdir="/WEB-INF/tags"%>
<cdc:page title="Cadastro de Produtos">

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="user"/>
    <div>
       Olá ${user.name}
    </div>
    </sec:authorize>


<body>

    <form:form servletRelativeAction="/products" method="post" commandName="product"
    			enctype="multipart/form-data">
    	
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
		         <div>
             <label for="summary">Sumario do livro</label>
             <input type="file" name="summary" id="summary"/>
             <form:errors path="summaryPath"/>
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
</cdc:page>
