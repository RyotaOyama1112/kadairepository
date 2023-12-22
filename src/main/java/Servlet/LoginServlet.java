package Servlet;

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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("user_id");
        String password = request.getParameter("password");

        String path = "";

        try {
            String sql = "SELECT userID,Busyo,KanriFlg FROM user情報 WHERE userID=? AND password=?";
            try (ResultSet res = DatabaseConnector.executeQuery(sql, userId, password)) {
                int kanriFlg = 0; 

                if (res.next()) {
                   HttpSession session = request.getSession(true);
                    session.setAttribute("user_id", res.getString("userID"));
                    session.setAttribute("user_busyo", res.getString("Busyo"));
                    session.setAttribute("user_KanriFlg", res.getInt("KanriFlg"));

                    LoginCheck lDAO = new LoginCheck();
                    kanriFlg = lDAO.getFlg(userId); 

                    if (kanriFlg == 1) {
                        path = "WEB-INF/view/loginJugyouin.jsp";
                    } else if (kanriFlg == 3) {
                        path = "WEB-INF/view/loginKanrisya.jsp";
                    }
                } else {
                    request.setAttribute("loginFailure", "ログインに失敗しました");
                    path = "WEB-INF/view/login.jsp";
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd2 = request.getRequestDispatcher(path);
        rd2.forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/login.jsp");
        rd.forward(request, response);
    }
}
