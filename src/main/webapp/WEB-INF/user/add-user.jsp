<%--Created by : User: maud_  --  Date: 08/12/2022  --  Time: 11:41--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un utilisateur</title>
</head>
<body>
    <jsp:include page="../header.jsp">
        <jsp:param name="errorMsg" value="${requestScope.error}"/>
        <jsp:param name="currentPage" value="addUser"/>
    </jsp:include>

<%-- Todo Maud : voir comment utiliser plutot une grid ici pour avoir un bel affichage --%>
    <main>
        <div class="content-header">
            <h2>Ajouter un utilisateur</h2>
        </div>

        <div class="main-content">
            <form action="${pageContext.request.contextPath}/users/add" method="post">

                <div class="form-fields">
                    <div class="form-field">
                        <div class="form-label">
                            <label for="pseudo">Identifiant</label>
                        </div>
                        <div class="form-value">
                            <input id="pseudo" type="text" name="pseudo">
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="password">Mot de passe</label>
                        </div>
                        <div class="form-value">
                            <input id="password" type="password" name="password">
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="email">Email</label>
                        </div>
                        <div class="form-value">
                            <input id="email" type="email" name="email">
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="city">Ville</label>
                        </div>
                        <div class="form-value">
                            <input id="city" type="text" name="city">
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="lastname">Nom</label>
                        </div>
                        <div class="form-value">
                            <input id="lastname" type="text" name="lastname">
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="firstname">Prénom</label>
                        </div>
                        <div class="form-value">
                            <input id="firstname" type="text" name="firstname">
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="isAdmin">Administrateur</label>
                        </div>
                        <div class="form-value">
                            <input id="isAdmin" type="checkbox" name="admin">
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="isSuperAdmin">Super administrateur</label>
                        </div>
                        <div class="form-value">
                            <input id="isSuperAdmin" type="checkbox" name="superAdmin">
                        </div>
                    </div>
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
