<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.*"%>
<jsp:useBean id="ydto" scope="request" class="bean.yukyuDTO" />
<jsp:useBean id="msg" scope="request" class="java.lang.String" />
<%
String url_name = "http://localhost:8080/Yukyu/login.jsp";
%>

<html>
<head>
<title>新規登録</title>
</head>
<body>
	<div style="text-align: center">
		<h2 style="text-align: center">新規登録画面</h2>
		<hr style="height: 3; background-color: #0000ff" />
		<h3><%=msg%></h3>
		<br> 登録する情報を入力してください。
		<form action="/Yukyu/yukyu" method="post">
			<table style="margin: 0 auto">
				<tr>
					<td style="width: 60">ID</td>
					<td><input type=text size="30" name="id"></input></td>
				</tr>
				<tr>
					<td style="width: 60">名前</td>
					<td><input type=text size="30" name="name"></input></td>
				</tr>
				<tr>
					<td style="width: 70">パスワード</td>
					<td><input type=text size="30" name="password"></input></td>
				</tr>
				<tr>
					<td style="width: 60">権限</td>
					<td><select name="kanriFlg">
							<option value="3">管理者</option>
							<option value="1">従業員</option>
					</select></td>
				</tr>
				<tr>
					<td style="width: 70">部署</td>
					<td><input type=text size="30" name="busyo"></input></td>
				</tr>
				<tr>
					<td colspan=2 style="text-align: center"><input type="submit"
						name="btn" value="登録"></td>
				</tr>
				<tr>
					<td colspan=2 style="text-align: center"><a
						href=<%=url_name%>>戻る</a></td>
				</tr>
			</table>
		</form>
		<br>
	</div>

</body>

</html