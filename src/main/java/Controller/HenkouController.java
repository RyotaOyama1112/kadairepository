package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.YukyuSinseiDAO;
import service.Transaction;

@WebServlet("/HenkouServlet")
public class HenkouController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String userId = request.getParameter("userid");
        String yoteibi = request.getParameter("yoteibi");
        int newStatus = Integer.parseInt(request.getParameter("status"));

        // YukyuSinseiDAOのインスタンスを作成
        YukyuSinseiDAO YukyuSinseiDAO = new YukyuSinseiDAO();

     // トランザクションを実行
        Transaction.performTransaction(con -> {
            // updateStatusメソッドを呼び出してステータスを更新
            YukyuSinseiDAO.updateStatus(userId, yoteibi, newStatus);
            });

        request.getSession().setAttribute("message", "変更が完了しました。");
        
        String forwardJSP = "/WEB-INF/view/Syousai.jsp";
        System.out.println(forwardJSP);
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardJSP);
        dispatcher.forward(request, response);
    }
}