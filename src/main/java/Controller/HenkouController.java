package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.YukyuSinseiDAO;
import DTO.KanrisyaDTO;
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
        
    	// 全件検索を実行して結果を取得
    	YukyuSinseiDAO sdao = new YukyuSinseiDAO();
    	KanrisyaDTO kdto = sdao.select2();
    	request.setAttribute("kdto", kdto);
 
        String forwardJSP = "/WEB-INF/view/loginKanrisya.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardJSP);
        dispatcher.forward(request, response);
    }
}