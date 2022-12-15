<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: Kesavan
  Date: 08/12/2022
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Liste des utilisateurs</title>
</head>
<body>
  <jsp:include page="../header.jsp"><jsp:param name="errorMsg" value="${requestScope.error}"/></jsp:include>
  <main>
    <div class="content-header">
      <h2>Liste des utilisateurs</h2>
    </div>

      <c:forEach var ="user" items="${usersList}">
        <div style="display:flex"> Pseudo : <c:out value="${user.pseudo}"/> - Mail : <c:out value="${user.email}"/>
          <form method="post" action="${pageContext.request.contextPath}/users/delete">
            <input name="idUserDelete" type="hidden" value="${user.id}">
            <button type="submit"> Delete </button>
          </form>
          <button><a href="${pageContext.request.contextPath}/user/details?userId=${user.id}"> Détails </a></button>
        </div>
      </c:forEach>

  </main>
</body>
</html>
