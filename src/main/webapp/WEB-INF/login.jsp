<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Login</title>
    <link href="../resources/style/login.css" rel="stylesheet" type="text/css">
</head>
<body id="loginPage">
    <c:if test="${requestScope.loginFail}">
        <div class="error-message">
            <p><c:out value="${requestScope.error}"/></p>
        </div>
    </c:if>
    <main class="login-container">
        <h1>Klend'art</h1>

        <div class="main-content">
            <form method="post" action="${pageContext.request.contextPath}/login">
                <div class="form-field">
                    <div class ="form-label hidden">
                        <label for="username">Identifiant</label>
                    </div>
                    <div class="form-input icon-user-b">
                        <input id="username" type="text" name="username" placeholder="Identifiant">
                    </div>
                </div>
                <div class="form-field">
                    <div class="form-label hidden">
                        <label for="password">Mot de passe</label>
                    </div>
                    <div class="form-input icon-key-b">
                        <input id="password" type="password" name="password" placeholder="Mot de passe">
                    </div>
                </div>
                <button type="submit">Se connecter</button>
            </form>
            <form method="post" action="${pageContext.request.contextPath}/init">
                <button type="submit">Init database</button>
            </form>
        </div>
    </main>
</body>
</html>