<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="fr">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <jsp:include page="../header.jsp">
        <jsp:param name="errorMsg" value="${requestScope.error}"/>
        <jsp:param name="info" value="${requestScope.info}"/>
        <jsp:param name="currentPage" value="addUser"/>
    </jsp:include>

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
                            <label for="lastname">Nom</label>
                        </div>
                        <div class="form-value">
                            <input id="lastname" type="text" name="lastname">
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
                            <label for="firstname">Prénom</label>
                        </div>
                        <div class="form-value">
                            <input id="firstname" type="text" name="firstname">
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
                            <label for="city">Ville</label>
                        </div>
                        <div class="form-value">
                            <input id="city" type="text" name="city">
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="role">Role</label>
                        </div>
                        <div class="form-value">
                            <%--TODO : change input type--%>
<%--                            <input list="role" name="role" >--%>
<%--                                <datalist id="role">--%>
<%--                                    <option value="ROLE_USER"> User</option>--%>
<%--                                    <option value="ROLE_ADMIN"> Admin</option>--%>
<%--                                    <option value="ROLE_SUPER_ADMIN">Super Admin</option>--%>
<%--                                </datalist>--%>
                            <select class="combo" name="role" id="role">
                                <option value="ROLE_USER"> Utilisateur</option>
                                <option value="ROLE_ADMIN"> Administrateur</option>
                                <option value="ROLE_SUPER_ADMIN">Super Administrateur</option>
                            </select>
                        </div>
                    </div>
                    <%--<div class="form-field">
                        <div class="form-label">
                            <label for="isAdmin">Administrateur</label>
                        </div>
                        <div class="form-value">
                            <input id="isAdmin" type="checkbox" name="admin">
                        </div>
                    </div>
                    <div class="placeholder"></div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="isSuperAdmin">Super administrateur</label>
                        </div>
                        <div class="form-value">
                            <input id="isSuperAdmin" type="checkbox" name="superAdmin"
                                   <c:if test="${sessionScope.user.superAdmin != true}">disabled</c:if>>
                        </div>
                    </div>--%>
                </div>
                <div>
                    <button type="reset" class="cancel">Vider les champs</button>
                    <button type="submit" class="validate">Ajouter</button>
                </div>
            </form>
        </div>

    </main>

</body>
</html>
