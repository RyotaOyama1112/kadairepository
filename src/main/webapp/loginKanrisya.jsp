<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String url_name = "http://localhost:8080/Yukyu/login.jsp"; %>
<%@page import="bean.*" %>
<jsp:useBean id ="kdto" scope="request" class="bean.KanrisyaDTO" />
<jsp:useBean id ="msg" scope="request" class="java.lang.String" />
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>管理者用画面</title>
</head>
<body>
  <!-- リクエストスコープからログインユーザーのIDを取得する -->
  <%String userId = (String)session.getAttribute("user_id"); %>
  <%String userBusyo = (String)session.getAttribute("user_busyo"); %>

  <!-- メッセージを表示する -->
  <div>管理者用画面ログイン中</div>
  <%
  
  %>
  <h2>
    こんにちは！所属部署が<%=userBusyo %>の<%=userId %>さん!
  </h2>
  
    <form action="KanrisyaServlet" method="post">
    取得予定日<input type="text" name="yoteibi"> 
    氏名<input type="text" name="name"> 
    部署<input type="text" name="busyo"> 
    ステータス<select name="status"> 
              <option value="0">選択してください</option>
              <option value="1">申請中</option>
              <option value="3">承認済</option>
    <input type="submit" value="検索" name="btn">
  </form> 
  <h2><%= msg %></h2>
<table border="1">
  <tr>
  　<th width="50">no</th>
    <th width="50">有給日時</th>
    <th width="50">詳細ボタン</th>
  </tr>
<%
for(int i = 0; i < kdto.size(); i++){
    KanrisyaBean kb = kdto.get(i);
%>
  <tr>
  <td align="center"><%= i %></td>
    <td align="center"><%= kb.getYoteibi() %></td>
    <td align="center">
    <form action="Syousai" method="post">
    <button type="submit" value="<%= i %>">詳細</button>
    </form>
    </td>
  </tr>
<% } %>
</table><br />
  
  <div><a href=<%=url_name%>>戻る</a></div>      
</body>
</html>