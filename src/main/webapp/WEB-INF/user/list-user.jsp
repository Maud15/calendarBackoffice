<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des utilisateurs</title>
    <link href="../../resources/style/list-user.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../header.jsp"><jsp:param name="errorMsg" value="${requestScope.error}"/></jsp:include>
<main>
    <div class="content-header">
        <h2>Liste des utilisateurs</h2>
    </div>
    <div class="main-content">
        <table>
            <thead>
            <tr>
                <td> Pseudo</td>
                <td> Email </td>
                <td> Supprimer</td>
                <td> Détails</td>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="user" items="${usersList}">
                <tr>
                    <td>
                        <c:out value="${user.pseudo}"/>
                    </td>
                    <td>
                        <c:out value="${user.email}"/>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/users/delete">
                            <input name="idUserDelete" type="hidden" value="${user.id}">
                            <button type="submit"> Delete </button>
                        </form>
                    </td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/user/details?userId=${user.id}">
                            <input name="userId" type="hidden" value="${user.id}">
                            <button type="submit"> Détails</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>
