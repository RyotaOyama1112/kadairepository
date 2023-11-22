<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String url_name = "http://localhost:8080/Yukyu/loginKanrisya.jsp";
%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <%
  KanrisyaBean kb = kdto.get();
  %>
  
  <div><%= kb.getYoteibi() %></div>
  <div><%= kb.getName() %></div>
  <div><%= kb.getBusyo() %></div>
  <div><%= kb.getStatus_name() %></div>
  <form action="henkou" method="post">
  <select name="status"> 
  <option value="0">選択してください</option>
  <option value="1">申請中</option>
  <option value="3">承認済</option>
  <input type="submit"name="btn" value="変更">
  </form>
  <a>href=<%=url_name%>>戻る</a>
</body>
</html>