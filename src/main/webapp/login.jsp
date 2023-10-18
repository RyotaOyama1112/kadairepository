<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String url_name = "http://localhost:8080/Yukyu/insertForm.jsp";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<!-- リクエストスコープからエラーメッセージを受け取る -->
	<%
	String failureMessage = (String) request.getAttribute("loginFailure");
	%>

	<!-- エラーメッセージが存在するときだけ表示する -->
	<%
	if (failureMessage != null) {
	%>
	<%=failureMessage%>
	<%
	}
	%>

	<!-- ログインフォーム。ユーザーIDとパスワードの入力を行う -->
	<form action="LoginServlet" method="post">
		ID<input type="text" name="user_id"> 
		PASSWORD<input type="text" name="password"> 
		<input type="submit"value="ログイン">
	</form>

	<a href=<%=url_name%>>新規登録</a>
</html>