<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.*" %>
<jsp:useBean id="sdto" scope="request" class="DTO.SyutokuYoteibiDTO" />
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>従業員用画面</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body class="container mt-5">
    <!-- リクエストスコープからログインユーザーのIDを取得する -->
    <% String userId = (String)session.getAttribute("user_id"); %>
    <% String userBusyo = (String)session.getAttribute("user_busyo"); %>

    <!-- メッセージを表示する -->
    <div class="alert alert-info">従業員用画面ログイン中</div>

    <h2>
        こんにちは！所属部署が<%=userBusyo %>の<%=userId %>さん!
    </h2>

    <form action="SinseiServlet" method="post">
        <div class="form-group">
            <label for="yoteibi">有給申請予定日</label>
            <input type="date" name="yoteibi" class="form-control">
        </div>
        <input type="submit" value="申請" name="btn" class="btn btn-primary d-flex justify-content-center mx-auto">
    </form>

    <table class="table table-bordered mt-3">
        <thead>
            <tr>
                <th scope="col">有給取得予定日</th>
            </tr>
        </thead>
        <form action="SyutokuYoteibi" method="post">
            <tr>
                <td align="center"><input type="submit" value="表示" class="btn btn-primary"></td>
            </tr>
        </form>
        <% for(int i = 0; i < sdto.size(); i++){
            SyutokuYoteibiBean sb = sdto.get(i);
        %>
        <tr>
            <td align="center"><%= sb.getYoteibi() %></td>
        </tr>
        <% } %>
    </table>

    <form action="/Yukyu/LoginForm" class="d-flex justify-content-center">
        <input type="submit" value="戻る" class="btn btn-secondary">
    </form>
    
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</body>
</html>