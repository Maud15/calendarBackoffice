<%--Created by : User: maud_  --  Date: 09/12/2022  --  Time: 14:33--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<head>
    <link href="../resources/style/main.css" rel="stylesheet" type="text/css">
</head>
<header>
    <h1 class='admin'>Klend'art</h1>
</header>

<c:choose>
    <c:when test="${not empty param.errorMsg}">
        <div class="error-message">
            <p><c:out value="${param.errorMsg}"/></p>
        </div>
    </c:when>
</c:choose>