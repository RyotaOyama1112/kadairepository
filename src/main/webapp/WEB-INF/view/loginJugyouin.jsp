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

        <div class="text">
            <!-- バリデーションエラーメッセージの表示 -->
            <%
                String msg = (String) request.getAttribute("msg");
                if (msg != null) {
            %>
            <%= msg %>
            <%
                }
            %>
        </div>

    <form action="SinseiServlet" method="post" class="text-right" id="syutokuForm">
        <div class="form-group">
            <label for="yoteibi">有給申請予定日</label>
            <input type="date" name="yoteibi" class="form-control">
        </div>
        <input type="submit" value="申請" name="btn" class="btn btn-primary">
    </form>

    <table class="table table-bordered mt-3">
<thead>
    <tr>
        <th scope="col" style="text-align: center;">有給取得予定日</th>
        <th scope="col" style="text-align: center;">申請者</th>
        <th scope="col" style="text-align: center;">部署</th>
    </tr>
</thead>

       <!--  <form action="SyutokuYoteibi" method="post" class="text-right" id="updateForm">
            <tr>
                <td align="right"><input type="submit" value="更新" class="btn btn-primary"></td>
            </tr>
        </form>-->
        <% for(int i = 0; i < sdto.size(); i++){
            SyutokuYoteibiBean sb = sdto.get(i);
        %>
        <tr>
            <td align="center"><%= sb.getYoteibi() %></td>
            <td align="center"><%= sb.getName() %></td>            
            <td align="center"><%= sb.getBusyo() %></td>            
        </tr>
        <% } %>
    </table>

    <!-- ログアウトボタンを右上に配置 -->
    <form action="/Yukyu/LoginForm" class="position-fixed" style="top: 20px; right: 20px;">
        <input type="submit" value="ログアウト" class="btn btn-secondary">
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
