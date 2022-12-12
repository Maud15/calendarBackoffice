<%--Created by : User: maud_  --  Date: 08/12/2022  --  Time: 11:41--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un utilisateur</title>
</head>
<body>
    <jsp:include page="../header.jsp"><jsp:param name="errorMsg" value="${requestScope.error}"/></jsp:include>

<%-- Todo Maud : voir comment utiliser plutot une grid ici pour avoir un bel affichage --%>
    <main>
        <div class="content-header">
            <h2>Ajouter un utilisateur</h2>
        </div>

        <div class="main-content">
            <form action="${pageContext.request.contextPath}/users/add" method="post">
                <div>
                    <label for="userEmail">Email</label>
                    <input id="userEmail" type="text" name="email">
                    <label for="userPseudo">Pseudo</label>
                    <input id="userPseudo" type="text" name="pseudo">
                    <label for="userPassword">Mot de passe</label>
                    <input id="userPassword" type="password" name="password">
                </div>
                <div>
                    <label for="isAdmin">Est admin ? (0, 1)</label>
                    <input id="isAdmin" type="checkbox" name="admin">
                    <label for="firstname">Prénom</label>
                    <input id="firstname" type="text" name="firstname">
                    <label for="lastname">Nom</label>
                    <input id="lastname" type="text" name="lastname">
                    <label for="city">Ville</label>
                    <input id="city" type="text" name="city">
                </div>

                <div>
                    <button>Ajouter</button>
                </div>
            </form>
        </div>

        <%--todo : remove, tmp button while waiting for userList jsp--%>
        <div class="content-header">
            <h2>Afficher un profil</h2>
        </div>

        <div class="main-content">
            <form method="get" action="${pageContext.request.contextPath}/user/details">
                <div>
                    <label for="userId">User Id</label>
                    <input id="userId" name="userId" type = number>
                    <button>Show</button>
                </div>
            </form>
        </div>
    </main>

</body>
</html>
