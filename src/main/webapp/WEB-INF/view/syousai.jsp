<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>詳細ページ</title>
</head>
<body>

    <%
    // セッションから情報を取得
    String userid = (String)session.getAttribute("userid");
    String name = (String)session.getAttribute("name");
    String busyo = (String)session.getAttribute("busyo");
    int status_name = (int)session.getAttribute("status_name");
    %>

    <div>ユーザーID: <%= userid %></div>
    <div>氏名: <%= name %></div>
    <div>部署: <%= busyo %></div>
    <div>ステータス: <%= status_name %></div>

<form action="/Yukyu/SyousaiServlet" method="post">
    <select name="status"> 
        <option value="0">選択してください</option>
        <option value="1">申請中</option>
        <option value="3">承認済</option>
    </select>
    <input type="submit" name="btn" value="変更">
</form>

    
    <a href="/Yukyu/WEB-INF/view/loginKanrisya.jsp">戻る</a>

</body>
</html>
