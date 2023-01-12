<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/x-icon" href="../../resources/assets/favicon.ico">
    <link href="../../resources/style/table.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../header.jsp">
    <jsp:param name="errorMsg" value="${requestScope.error}"/>
    <jsp:param name="info" value="${requestScope.info}"/>
    <jsp:param name="currentPage" value="usersList"/>
</jsp:include>
<main>
    <div class="content-header">
        <h2>Liste des utilisateurs</h2>
    </div>
    <div class="main-content">
        <table>
            <thead>
                <tr>
                    <th>Pseudo</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th aria-colspan="2">Actions</th>
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
                        <c:out value="${user.roleList.stream().findFirst().get().getName()}"/>
                    </td>
                    <td class="actions">
                        <form method="get" action="${pageContext.request.contextPath}/user/details?userId=${user.id}">
                            <input name="userId" type="hidden" value="${user.id}">
                            <button type="submit"> Détails</button>
                        </form>
                        <form method="post" action="${pageContext.request.contextPath}/users/delete">
                            <input name="idUserDelete" type="hidden" value="${user.id}">
                            <button class="alert" type="submit"> Delete </button>
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
