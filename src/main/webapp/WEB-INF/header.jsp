<%--Created by : User: maud_  --  Date: 09/12/2022  --  Time: 14:33--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<head>
    <title>Klend'art</title>
    <link rel="icon" type="image/x-icon" href="../resources/assets/favicon.ico">
    <link href="../resources/style/header.css" rel="stylesheet" type="text/css">
    <script defer type="text/javascript" src="../resources/script/header.js"></script>
</head>
<c:choose>
    <c:when test="${(\"ROLE_SUPER_ADMIN\").equals(sessionScope.role)}">
<header class='role super-admin'>
    </c:when>
    <c:otherwise>
<header class='role admin'>
    </c:otherwise>
</c:choose>

    <h1>Klend'art</h1>
    <c:if test="${not param.currentPage.equals(\"usersList\")}">
    <form style="margin:0" action="${pageContext.request.contextPath}/users" method="get">
        <button>Liste des utilisateurs</button>
    </form>
    </c:if>
    <c:if test="${(\"ROLE_SUPER_ADMIN\").equals(sessionScope.role) && not param.currentPage.equals(\"addUser\")}">
    <form style="margin:0" action="${pageContext.request.contextPath}/users/add" method="get">
        <button>Créer un nouvel utilisateur</button>
    </form>
    </c:if>

    <form style="margin:0; flex: 2;" action="${pageContext.request.contextPath}/logout" method="post">
        <button class="logout">Se déconnecter</button>
    </form>
</header>


<c:choose>
    <c:when test="${not empty param.errorMsg}">
        <div class="error-message">
            <p><c:out value="${param.errorMsg}"/></p>
        </div>
    </c:when>
    <c:when test="${not empty param.info}">
        <div id="info-msg" class="info-message">
            <p><c:out value="${param.info}"/></p>
        </div>
    </c:when>
</c:choose>
