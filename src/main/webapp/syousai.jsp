<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%
    String url_name = "http://localhost:8080/Yukyu/loginKanrisya.jsp";
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>申請詳細</title>
</head>
<body>

    <c:set var="kb" value="" /> <!-- 変数 kb を宣言 -->

    <c:forEach var="kb" items="${kdto.list}">
        <div>日付: <%= kb.getYoteibi() %></div>
        <div>名前: <%= kb.getName() %></div>
        <div>部署: <%= kb.getBusyo() %></div>
        <div>ステータス: <%= kb.getStatus_name() %></div>
        
        <form action="henkou" method="post">
            <select name="status">
                <option value="0">選択してください</option>
                <option value="1">申請中</option>
                <option value="3">承認済</option>
            </select>
            <input type="submit" name="btn" value="変更">
        </form>
    </c:forEach>

    <a href="<%= url_name %>">戻る</a>
</body>
</html>
