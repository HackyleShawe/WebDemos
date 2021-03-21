<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Index</title>
</head>
<body>

<%
  pageContext.getRequest().setAttribute("name", "kyle");
%>


<c:out value="${name}"/>

<form:input path="id" />


</body>
</html>
