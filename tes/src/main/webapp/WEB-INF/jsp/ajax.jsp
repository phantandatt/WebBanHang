<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h1>Demo ajax</h1>
	<button type="button" value="hello" id="btn" onclick="getAll()">click</button>
	<div class="result"></div>
</body>
<script>
	function getAll() {
		$.ajax({

			url : "https://reqres.in/api/product",
			type : "get",
			dataTye : "json",
			success(response){
				console.log(response)
			}

		});
	}
</script>
</html>