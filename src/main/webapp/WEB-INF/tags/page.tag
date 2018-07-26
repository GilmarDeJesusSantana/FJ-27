<%@attribute name="title" required="true"%>
<%@attribute name="bodyClass" required="false"%>
<%@attribute name="extraScripts" fragment="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html class="no-js" lang="pt">
<head>
	
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>${title}</title>
	

</head>

<body class="${bodyClass}">
	<jsp:doBody />
	
	<jsp:invoke fragment="extraScripts" />
</body>
</html>
