<%--Created by : User: maud_  --  Date: 08/12/2022  --  Time: 11:41--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un utilisateur</title>
</head>
<body>
<%--    <jsp:include page="../header.jsp"><jsp:param name="errorMsg" value="${error}"/></jsp:include>--%>

<form action="${pageContext.request.contextPath}/users/add" method="post">
<%-- Todo Maud : voir comment utiliser plutot une grid ici pour avoir un bel affichage --%>
    <%--<div class="formLabels">
        <label for="userPseudo"></label>
    </div>--%>
    <div class="formInputs">
        <label for="userEmail">Email</label>
        <input id="userEmail" type="text" name="userEmail">
        <label for="userPseudo">Pseudo</label>
        <input id="userPseudo" type="text" name="userPseudo">
        <label for="userPassword">Mot de passe</label>
        <input id="userPassword" type="password" name="userPassword">
        <label for="isAdmin">Est admin ? (true, false)</label>
        <input id="isAdmin" type="text" name="isAdmin">
    </div>

    <button>Ajouter</button>
</form>


</body>
</html>
