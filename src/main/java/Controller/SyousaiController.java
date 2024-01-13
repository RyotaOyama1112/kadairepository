package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DatabaseConnector;
import DAO.YukyuSinseiDAO;

@WebServlet("/SyousaiServlet")
public class SyousaiController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // SousaiServletで取得したuserid
        String userId = request.getParameter("userid");

        // データベースからユーザー情報を取得してセッションにセット
        try (Connection connection = DatabaseConnector.getConnection()) {
            YukyuSinseiDAO.getUserInfo(connection, request, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        String forwardJSP = "WEB-INF/view/Syousai.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardJSP);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    }
}
