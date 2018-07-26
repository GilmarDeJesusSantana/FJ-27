<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Minha Pagina de login</title>
</head>
<body>
	<h1>Bem Vindo a Casa do codigo</h1>
	    <form:form servletRelativeAction="/login" method="post">
	    <c:if test="${param.error != null}">
	    <div class="alert alert-error">
	    <spring:message code="message.badCredentials"/>
	    </div>
	    
	    </c:if>
        <div>
            <label> Usuário: </label>
        	<input type='text' name='username' value=''>
        </div>
       
        <div>
            <label>Senha:</label>
                <input type='password' name='password' />
        </div>
        <div></div>
        <div>
            <input name="submit" type="submit" value="Login" />
        </div>
    </form:form>
	
</body>
</html>