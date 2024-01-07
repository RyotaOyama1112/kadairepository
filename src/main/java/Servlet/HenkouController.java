package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.KanrisyaDAO;

@WebServlet("/HenkouServlet")
public class HenkouController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String userId = request.getParameter("userid");
        String yoteibi = request.getParameter("yoteibi");
        int newStatus = Integer.parseInt(request.getParameter("status"));

        // KanrisyaDAOのインスタンスを作成
        KanrisyaDAO kanrisyaDAO = new KanrisyaDAO();

        // updateStatusメソッドを呼び出してステータスを更新
        kanrisyaDAO.updateStatus(userId, yoteibi, newStatus);

        String forwardJSP = "/WEB-INF/view/Syousai.jsp";
        System.out.println(forwardJSP);
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardJSP);
        dispatcher.forward(request, response);
    }
}
