<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <!-- リクエストスコープからログインユーザーのIDを取得する -->
  <%String userId = (String)request.getAttribute("user_id"); %>
  <%String userBusyo = (String)request.getAttribute("user_busyo"); %>

  <!-- メッセージを表示する -->
  <div>管理者用画面ログイン中</div>
  <%
  
  %>
  <h2>
    こんにちは！所属部署が<%=userBusyo %>の<%=userId %>さん!
  </h2>

  </form>
</body>
</html>