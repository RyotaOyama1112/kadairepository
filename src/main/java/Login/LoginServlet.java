package Login;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DatabaseConnector;
import DAO.LoginCheck;

//DatabaseConnectorクラスの変更は不要と仮定

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;

 public LoginServlet() {
     super();
 }
//送られてきたユーザーIDとパスワードを元にDBに接続しログイン認証を行う
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

	// ユーザーから送信されたユーザーIDとパスワードを取得する。
     String userId = request.getParameter("user_id");
     String password = request.getParameter("password");

  // ログイン認証後に遷移する先を格納する
     String path = "";

     try {
         String sql = "SELECT userID,Busyo,KanriFlg FROM user情報 WHERE userID=? AND password=?";
         try (ResultSet res = DatabaseConnector.executeQuery(sql, userId, password)) {

        	// ユーザーIDとパスワードが一致するユーザーが存在した時
        	 if (res.next()) {
                 HttpSession session = request.getSession(true);
                 session.setAttribute("user_id", res.getString("userID"));
                 session.setAttribute("user_busyo", res.getString("Busyo"));
                 session.setAttribute("user_KanriFlg", res.getInt("KanriFlg"));

                 LoginCheck lDAO = new LoginCheck();
                 int kanriFlg = lDAO.getFlg(userId);

                 if (kanriFlg == 1) {
                     path = "loginJugyouin.jsp";
                  // 従業員画面に遷移する
                 } else if (kanriFlg == 3) {
                	// 管理者画面に遷移する
                     path = "loginKanrisya.jsp";
                 }
             } else {
            	// ログイン失敗の文言を追加する
                 request.setAttribute("loginFailure", "ログインに失敗しました");
                 
              // ログインに失敗したときはもう一度ログイン画面を表示する
                 path = "login.jsp";
             }
         }
     } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();     }

     RequestDispatcher rd2 = request.getRequestDispatcher(path);
     rd2.forward(request, response);
 }

 protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     doPost(request, response);
 }
}
