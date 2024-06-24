<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<style>
/* styles.css */
body {
	font-family: Arial, sans-serif;
	background-color: #f0f2f5;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.container {
	background-color: #ffffff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	width: 400px;
	max-width: 100%;
}

h2 {
	text-align: center;
	margin-bottom: 20px;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	margin-bottom: 5px;
}

.form-group input {
	width: calc(100% - 22px);
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.form-group input[type="submit"] {
	background-color: #007bff;
	color: #fff;
	border: none;
	cursor: pointer;
	transition: background-color 0.3s;
}

.form-group input[type="submit"]:hover {
	background-color: #0056b3;
}

.mess {
	color: red;
	text-align: center;
	margin-bottom: 15px;
}

.error {
	color: red;
	margin-top: 2px;
}

@media ( max-width : 600px) {
	.container {
		width: 100%;
		padding: 10px;
	}
}
</style>

<body>
	<div class="container">
		<h2>Login</h2>
		<form action="/loginPerfome" method="post">
			<div class="form-group">
				<label>Username:</label> <input type="text" name="username" />
			</div>
			<div class="form-group">
				<label>Password:</label> <input type="password" name="password" />
			</div>
			<div>
				<button class="btn btn-success" type="submit">Login</button>
			</div>
			<a href="/registerPerfome"> Register </a>
			<c:if test="${param.error != null}">
				<div style="color: red;">Invalid username or password.</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div style="color: green;">You have been logged out.</div>
			</c:if>
		</form>
	</div>
</body>
</html>
