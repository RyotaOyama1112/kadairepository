<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.*" %>
<jsp:useBean id ="kdto" scope="request" class="DTO.KanrisyaDTO" />
<jsp:useBean id ="msg" scope="request" class="java.lang.String" />
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>管理者用画面</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <!-- リクエストスコープからログインユーザーのIDを取得する -->
        <% String userId = (String)session.getAttribute("user_id"); %>
        <% String userBusyo = (String)session.getAttribute("user_busyo"); %>

        <!-- メッセージを表示する -->
        <div class="alert alert-info">管理者用画面ログイン中</div>

        <h2>
            こんにちは！所属部署が<%=userBusyo %>の<%=userId %>さん!
        </h2>

        <form id="searchForm" action="KanrisyaServlet" method="post">
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="yoteibi">取得予定日</label>
                    <input type="text" class="form-control" name="yoteibi">
                </div>
                <div class="form-group col-md-3">
                    <label for="name">氏名</label>
                    <input type="text" class="form-control" name="name">
                </div>
                <div class="form-group col-md-3">
                    <label for="busyo">部署</label>
                    <input type="text" class="form-control" name="busyo">
                </div>
                <div class="form-group col-md-3">
                    <label for="status">ステータス</label>
                    <select class="form-control" name="status">
                        <option value="0">選択してください</option>
                        <option value="1">申請中</option>
                        <option value="3">承認済</option>
                    </select>
                </div>
            </div>
            <div class="text-right">
                <input type="hidden" name="btn" value="検索">
                <input type="submit" value="検索" class="btn btn-primary ml-auto">
            </div>
        </form>

        <h2 class="mt-4"><%= msg %></h2>

        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th scope="col">No</th>
                    <th scope="col">有給日時</th>
                    <th scope="col">UserID</th>
                    <th scope="col">申請者</th>
                    <th scope="col">部署名</th>
                    <th scope="col">詳細ボタン</th>
                </tr>
            </thead>
            <tbody>
                <% for(int i = 0; i < kdto.size(); i++){
                    KanrisyaBean kb = kdto.get(i);
                %>
                <tr>
                    <td align="center"><%= i + 1 %></td>
                    <td align="center"><%= kb.getYoteibi() %></td>
                    <td align="center"><%= kb.getUserID() %></td>
                    <td align="center"><%= kb.getName() %></td>
                    <td align="center"><%= kb.getBusyo() %></td>
                    <td align="center">
                        <form action="/Yukyu/SyousaiServlet" method="post" >
                            <input type="hidden" name="userid" id="userid" value="<%= kb.getUserID() %>">
                            <button type="submit" class="btn btn-info">詳細</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <form action= "/Yukyu/LoginForm" class="position-fixed" style="top: 20px; right: 20px;">
        <button type="submit" class="btn btn-secondary">ログアウト</button>
    </form>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>
