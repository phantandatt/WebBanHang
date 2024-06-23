<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body>
	<h2>Login Page</h2>
	<form action="/loginPerfome" method="post">
		<div>
			<label>Username:</label> <input type="text" name="username" />
		</div>
		<div>
			<label>Password:</label> <input type="password" name="password" />
		</div>
		<div>
			<button type="submit">Login</button>
		</div>
		<a href="/registerPerfome"> Register </a>
		<c:if test="${param.error != null}">
			<div style="color: red;">Invalid username or password.</div>
		</c:if>
		<c:if test="${param.logout != null}">
			<div style="color: green;">You have been logged out.</div>
		</c:if>
	</form>
</body>
</html>
