<%--Created by : User: maud_  --  Date: 09/12/2022  --  Time: 11:22--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>User details</title>
</head>
<body>
    <jsp:include page="../header.jsp"><jsp:param name="errorMsg" value="${requestScope.error}"/></jsp:include>
    <main>
        <div class="main-content">

            <div class="content-header">
                <h2>Profil utilisateur <span class="userPseudoTag">${requestScope.user.getPseudo()}</span></h2>
                <button type="button" >Edit</button>
            </div>

            <form aria-disabled="true">
                <div>
                    <label for="email">Email</label>
                    <input  type="email" name="email" id="email" value="${requestScope.user.email}" disabled>

                    <label for="pseudo">Identifiant</label>
                    <input type="text" name="pseudo" id="pseudo" value="${requestScope.user.pseudo}" disabled>
                    <label for="firstname">Prénom</label>
                    <input type="text" name="firstname" id="firstname" value="${requestScope.user.firstname}" disabled>

                    <label for="lastname">Nom</label>
                    <input type="text" name="lastname" id="lastname" value="${requestScope.user.lastname}" disabled>

                    <label for="city">Ville</label>
                    <input type="text" name="city" id="city" value="${requestScope.user.city}" disabled>
                    <label for="isAdmin">Administrateur</label>
                    <input type="checkbox" name="admin" id="isAdmin"
                           value="<c:if test="${requestScope.user.admin != true}">0</c:if>" disabled>
                    <label for="isSuperAdmin">Super Administrateur</label>
                    <input type="checkbox" name="superAdmin" id="isSuperAdmin"
                           value="<c:if test="${requestScope.user.superAdmin != true}">0</c:if>" disabled>
                </div>

                <table style="border-collapse: collapse;background: white;border: 1px solid black;margin: 20px 100px">
                    <thead>
                        <tr>
                            <th>Calendriers de ${requestScope.user.pseudo}</th>
                            <th>Droits</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cal" items="${requestScope.user.calendarRightsList}">
                            <tr>
                                <td><c:out value="cal."/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </form>
        </div>
    </main>
</body>
</html>
