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
<p>Voici la liste des utilisateurs : <br> </p>
<c:forEach var ="user" items="${usersList}">
<p> Pseudo : <c:out value="${user.pseudo}"/> - Mail : <c:out value="${user.email}"/>  <button> Delete </button> <button> Details </button> </p>
</c:forEach>


</body>
</html>
