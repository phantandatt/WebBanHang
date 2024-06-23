<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Register page</h2>    
    <form action="/registerPerfome" method="post">
        <div>
            <label>Username:</label>
            <input type="text" name="username"/>
        </div>
        <div>
            <label>Password:</label>
            <input type="password" name="password"/>
        </div>
        <a href="/loginPerfome"> Login </a>
        <div>
            <button type="submit">Regis</button>
        </div>
        <c:if test="${error}">
            <div style="color: red;">${error}</div>
        </c:if>
    </form>
</body>
</html>