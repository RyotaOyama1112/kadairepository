<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>詳細ページ</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body class="bg-light container mt-5 p-4">

    <div id="messageDiv" class="mb-4">
        <%
            // セッションから情報を取得
            String userid = (String)session.getAttribute("userid");
            String name = (String)session.getAttribute("name");
            String busyo = (String)session.getAttribute("busyo");
            int status_name = (int)session.getAttribute("status_name");

            java.util.Date yoteibi = (java.util.Date)session.getAttribute("yoteibi");
            // SimpleDateFormatを使って日付を文字列にフォーマット
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedYoteibi = (yoteibi != null) ? dateFormat.format(yoteibi) : "";
        %>

        <%
            // セッションからメッセージを取得
            String message = (String)session.getAttribute("message");
            // メッセージが存在する場合に表示
            if (message != null && !message.isEmpty()) {
        %>
        <div class="alert alert-danger">
            <%= message %>
        </div>
        <%
            // メッセージを表示したら、セッションから削除
            session.removeAttribute("message");
            }
        %>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="mb-3">ユーザーID: <%= userid %></div>
            <div class="mb-3">氏名: <%= name %></div>
            <div class="mb-3">部署: <%= busyo %></div>
            <div class="mb-3">ステータス: <%= status_name %></div>
            <div class="mb-3">予定日: <%= formattedYoteibi %></div>
        </div>

        <div class="col-md-6">
            <form action="/Yukyu/HenkouServlet" method="post" class="mb-3">
                <input type="hidden" name="userid" value="<%= userid %>">
                <input type="hidden" name="yoteibi" value="<%= formattedYoteibi %>">

                <div class="form-group">
                    <label for="status">ステータス変更:</label>
                    <select name="status" class="form-control"> 
                        <option value="0">選択してください</option>
                        <option value="1">申請中</option>
                        <option value="3">承認済</option>
                    </select>
                </div>
                <input type="submit" name="btn" value="変更" class="btn btn-primary">
                <a href="/Yukyu/LoginKanrisyaForwardController" class="btn btn-secondary ml-2">戻る</a>
            </form>
        </div>
    </div>

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
