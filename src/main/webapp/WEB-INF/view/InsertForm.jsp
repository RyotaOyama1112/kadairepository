<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.*"%>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>新規登録</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        /* 新規登録画面のタイトルを中央に配置するスタイル */
        .title {
            text-align: center;
            margin-bottom: 30px; 
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-12 text-right mb-3">
                <form action="/Yukyu/LoginForm" method="get">
                    <button type="submit" class="btn btn-secondary">戻る</button>
                </form>
            </div>
        </div>

        <h2 class="title">新規登録画面</h2> <!-- タイトルを中央に配置 -->
        
                <!-- 登録完了メッセージを表示 -->
        <% String msg = (String) request.getAttribute("msg"); %>
        <% if (msg != null && !msg.isEmpty()) { %>
            <div class="alert alert-success" role="alert">
                <%= msg %>
            </div>
        <% } %>
        
                <%-- エラーメッセージがあれば表示 --%>
        <% List<String> errors = (List<String>) request.getAttribute("errors"); %>
        <% if (errors != null && !errors.isEmpty()) { %>
            <div class="alert alert-danger">
                <ul>
                    <% for (String error : errors) { %>
                        <li><%= error %></li>
                    <% } %>
                </ul>
            </div>
        <% } %>

        <form action="/Yukyu/yukyu" method="post">
            <div class="form-group row">
                <label for="id" class="col-sm-2 col-form-label">ID</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="id" name="id" required>
                </div>
            </div>

            <div class="form-group row">
                <label for="name" class="col-sm-2 col-form-label">名前</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
            </div>

            <div class="form-group row">
                <label for="password" class="col-sm-2 col-form-label">パスワード</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="password" name="password" required>
                </div>
            </div>

            <div class="form-group row">
                <label for="kanriFlg" class="col-sm-2 col-form-label">権限</label>
                <div class="col-sm-10">
                    <select class="form-control" id="kanriFlg" name="kanriFlg">
                        <option value="">権限を選択してください</option>
                        <option value="3">管理者</option>
                        <option value="1">従業員</option>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <label for="busyo" class="col-sm-2 col-form-label">部署</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="busyo" name="busyo" required>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-10 offset-sm-2 text-right">
                    <input type="submit" name="btn" value="登録" class="btn btn-primary">
                </div>
            </div>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
