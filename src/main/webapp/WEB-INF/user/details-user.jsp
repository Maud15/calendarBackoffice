<%--Created by : User: maud_  --  Date: 09/12/2022  --  Time: 11:22--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/x-icon" href="../../resources/assets/favicon.ico">
    <script defer type="text/javascript" src="../../resources/script/userDetails.js"></script>
</head>
<body>
    <jsp:include page="../header.jsp">
        <jsp:param name="errorMsg" value="${requestScope.error}"/>
        <jsp:param name="info" value="${requestScope.info}"/>
    </jsp:include>
    <main>
        <c:set var="role_userToModify" scope="session" value="${requestScope.user.roleList.stream().findFirst().get().getName()}"/>
        <c:set var="role_current_user" scope="session" value="${sessionScope.role}"/>
        <div id="userDetailsHeader" class="content-header">
            <h2>Profil utilisateur <span class="userPseudoTag">${requestScope.user.getPseudo()}</span></h2>
            <button id="editUser" type="button" >Edit</button>
        </div>


        <div class="main-content">

            <form id="formDetailsUser" class="aria-disabled" action="${pageContext.request.contextPath}/user/update" method="post">
                <input type="hidden" name="id" value="${requestScope.user.id}">
                <div class="form-fields">
                    <div class="form-field">
                        <div class="form-label">
                            <label for="pseudo">Identifiant</label>
                        </div>
                        <div class="form-value">
                            <input type="text" name="pseudo" id="pseudo" value="${requestScope.user.pseudo}" disabled>
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="lastname">Nom</label>
                        </div>
                        <div class="form-value">
                            <input type="text" name="lastname" id="lastname" value="${requestScope.user.lastname}" disabled>
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="email">Email</label>
                        </div>
                        <div class="form-value">
                            <input  type="email" name="email" id="email" value="${requestScope.user.email}" disabled>
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="firstname">Prénom</label>
                        </div>
                        <div class="form-value">
                            <input type="text" name="firstname" id="firstname" selected="${requestScope.user.firstname}" disabled>
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="city">Ville</label>
                        </div>
                        <div class="form-value">
                            <input type="text" name="city" id="city" value="${requestScope.user.city}" disabled>
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="role">Role</label>
                        </div>
                        <div class="form-value">
                            <%--TODO : change input type--%>

<%--                                <input type="text" name="role"  value="${requestScope.user.roleList.stream().findFirst().get().getName()}" disabled>--%>

                            <c:if test="${role_current_user == 'ROLE_ADMIN'}" >
                                <c:if test="${role_userToModify == 'ROLE_ADMIN'}">
                                    <select class="combo" name="role" id="role" value="${requestScope.user.roleList.stream().findFirst().get().getName()}" >
                                        <option  value="ROLE_ADMIN"> Administrateur</option>
                                        <option value="ROLE_USER" > Utilisateur</option>
                                    </select>
                                </c:if>
                                <c:if test="${role_userToModify == 'ROLE_USER'}">
                                    <select class="combo" name="role" id="role" value="${requestScope.user.roleList.stream().findFirst().get().getName()}" >
                                        <option value="ROLE_USER"> Utilisateur</option>
                                        <option value="ROLE_ADMIN"> Administrateur</option>
                                    </select>
                                </c:if>
                                <c:if test="${role_userToModify == 'ROLE_SUPER_ADMIN'}">
                                </c:if>
                            </c:if>
                            <c:if test="${role_current_user == 'ROLE_SUPER_ADMIN'}" >
                                <c:if test="${role_userToModify == 'ROLE_ADMIN'}">
                                    <select class="combo" name="role" id="role" value="${requestScope.user.roleList.stream().findFirst().get().getName()}" >
                                        <option  value="ROLE_ADMIN"> Administrateur</option>
                                        <option value="ROLE_USER" > Utilisateur</option>
                                        <option value="ROLE_SUPER_ADMIN">Super Administrateur</option>
                                    </select>
                                </c:if>
                                <c:if test="${role_userToModify == 'ROLE_USER'}">
                                    <select class="combo" name="role" id="role" value="${requestScope.user.roleList.stream().findFirst().get().getName()}" >
                                        <option value="ROLE_USER"> Utilisateur</option>
                                        <option value="ROLE_ADMIN"> Administrateur</option>
                                        <option value="ROLE_SUPER_ADMIN">Super Administrateur</option>
                                    </select>
                                </c:if>
                                <c:if test="${role_userToModify == 'ROLE_SUPER_ADMIN'}">
                                    <select class="combo" name="role" id="role" value="${requestScope.user.roleList.stream().findFirst().get().getName()}" >
                                        <option value="ROLE_SUPER_ADMIN">Super Administrateur</option>
                                        <option value="ROLE_USER"> Utilisateur</option>
                                        <option value="ROLE_ADMIN"> Administrateur</option>
                                    </select>
                                </c:if>
                            </c:if>

                        </div>
                    </div>
                    <%--<div class="form-field">
                        <div class="form-label">
                            <label for="isAdmin">Administrateur</label>
                        </div>
                        <div class="form-value">
                            <input type="checkbox" name="admin" id="isAdmin"
                                   value="<c:if test="${requestScope.user.admin != true}">0</c:if>" disabled>
                        </div>
                    </div>--%>
                    <%--<div class="form-field">
                        <div class="form-label">
                            <label for="isSuperAdmin">Super Administrateur</label>
                        </div>
                        <div class="form-input">
                            <input type="checkbox" name="superAdmin" id="isSuperAdmin"
                                   value="<c:if test="${requestScope.user.superAdmin != true}">0</c:if>"
&lt;%&ndash;                                   TODO: ajouter une info dans le sessionScope qui permette de savoir
                                             si notre utilisateur est SUPERADMIN = autorisé à changer la propriété SUPERADMIN d'un autre user&ndash;%&gt;
                                   class="<c:if test="${sessionScope.user.superAdmin != true}">forbidden</c:if>"
                                   disabled>
                        </div>
                    </div>--%>
                </div>
                <div class="form-buttons">
                    <button id="cancel-edit" class="cancel" type="reset">Annuler</button>
                    <button class="validate" type="submit">Valider</button>
                </div>

                <%--<table style="border-collapse: collapse;background: white;border: 1px solid lightgray;margin: 20px 100px">
                    <thead>
                        <tr>
                            <th style="padding: 5px 10px">Calendriers de <span class="user-data">${requestScope.user.pseudo}</span></th>
                            <th style="padding: 5px 10px">Droits</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cal" items="${requestScope.user.calendarRightsList}">
                            <tr>
                                <td><c:out value="cal."/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>--%>

            </form>

        </div>
    </main>
</body>
</html>
