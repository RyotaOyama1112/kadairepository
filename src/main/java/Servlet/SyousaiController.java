package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

@WebServlet("/SyousaiServlet")
public class SyousaiController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // SousaiServletで取得したuserid
        String userId = request.getParameter("userid");

        // データベースからユーザー情報を取得してセッションにセット
        getUserInfoAndSetSession(request, userId);

        String forwardJSP = "WEB-INF/view/Syousai.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardJSP);
        dispatcher.forward(request, response);
    }

    private void getUserInfoAndSetSession(HttpServletRequest request, String userId) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM 有給申請 WHERE userID = ?")) {
                statement.setString(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // データベースから取得した値をセッションにセット
                        HttpSession session = request.getSession();
                        session.setAttribute("userid", resultSet.getString("userID"));
                        session.setAttribute("name", resultSet.getString("Name"));
                        session.setAttribute("busyo", resultSet.getString("Busyo"));
                        session.setAttribute("sinseibi", resultSet.getDate("Sinseibi"));
                        session.setAttribute("yoteibi", resultSet.getDate("yoteibi"));
                        session.setAttribute("status_name", resultSet.getInt("status_name"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 適切なエラーハンドリングに変更すること
        } catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    }
}
