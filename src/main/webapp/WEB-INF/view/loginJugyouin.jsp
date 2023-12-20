<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String url_name = "http://localhost:8080/Yukyu/login.jsp"; %>
<%@page import="bean.*" %>
<jsp:useBean id ="sdto" scope="request" class="bean.SyutokuYoteibiDTO" />
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>従業員用画面</title>
</head>
<body>
	<!-- リクエストスコープからログインユーザーのIDを取得する -->
	<%String userId = (String)session.getAttribute("user_id"); %>
  <%String userBusyo = (String)session.getAttribute("user_busyo"); %>

	<!-- メッセージを表示する -->
	<div>従業員用画面ログイン中</div>
	<%
	
	%>
	<h2>
		こんにちは！所属部署が<%=userBusyo %>の<%=userId %>さん!
	</h2>
	
	  
  <form action="SinseiServlet" method="post">
    有給申請予定日<input type="date" name="yoteibi">
    <input type="submit"name="btn" value="申請">
    </form>
    
    <table border="1">
  <tr>
    <th >有給取得予定日</th>
  </tr>
   <form action="SyutokuYoteibi" method="post">
  <input type="submit" value="表示" />
<%
  for(int i = 0; i < sdto.size(); i++){
    SyutokuYoteibiBean sb = sdto.get(i);
%>
  <tr>
    <td align="center"><%= sb.getYoteibi() %></td>
  </tr>
<% } %>
    </form>
    </table>
  <div><a href=<%=url_name%>>戻る</a></div>
        
</body>
</html>