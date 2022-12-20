<%--Created by : User: maud_  --  Date: 09/12/2022  --  Time: 11:22--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>User details</title>
    <script defer type="text/javascript" src="../../resources/script/userDetails.js"></script>
</head>
<body>
    <jsp:include page="../header.jsp">
        <jsp:param name="errorMsg" value="${requestScope.error}"/>
        <jsp:param name="info" value="${requestScope.info}"/>
    </jsp:include>
    <main>
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
                            <label for="password">Mot de passe</label>
                        </div>
                        <div class="form-value">
                            <input type="password" name="password" id="password" value="" placeholder="********" disabled>
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
                            <label for="city">Ville</label>
                        </div>
                        <div class="form-value">
                            <input type="text" name="city" id="city" value="${requestScope.user.city}" disabled>
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
                            <label for="firstname">Prénom</label>
                        </div>
                        <div class="form-value">
                            <input type="text" name="firstname" id="firstname" value="${requestScope.user.firstname}" disabled>
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="isAdmin">Administrateur</label>
                        </div>
                        <div class="form-value">
                            <input type="checkbox" name="admin" id="isAdmin"
                                   value="<c:if test="${requestScope.user.admin != true}">0</c:if>" disabled
                                   <c:if test="${requestScope.user.admin == true}" > checked</c:if> >
                        </div>
                    </div>
                    <div class="form-field">
                        <div class="form-label">
                            <label for="isSuperAdmin">Super Administrateur</label>
                        </div>
                        <div class="form-input">
                            <input type="checkbox" name="superAdmin" id="isSuperAdmin"
                                   value="<c:if test="${requestScope.user.superAdmin != true}">0</c:if>"
<%--                                   TODO: ajouter une info dans le sessionScope qui permette de savoir
                                             si notre utilisateur est SUPERADMIN = autorisé à changer la propriété SUPERADMIN d'un autre user--%>
                                   class="<c:if test="${sessionScope.user.superAdmin != true}">forbidden</c:if>"
                                   disabled
                                   <c:if test="${requestScope.user.superAdmin == true}" > checked</c:if>  >
                        </div>
                    </div>
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
