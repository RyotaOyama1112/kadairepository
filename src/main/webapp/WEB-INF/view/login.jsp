<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	<form action= "/Yukyu/LoginServlet" method="post">
		ID<input type="text" name="user_id"> 
		PASSWORD<input type="text" name="password"> 
		<input type="submit"value="ログイン">
	</form>

	<form action= "/Yukyu/InsertFormServlet">
	 <input type="submit"value="新規登録">
　</form>
</html>