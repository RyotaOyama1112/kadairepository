package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.YukyuInsert;

@WebServlet("/SinseiServlet")
public class SinseiServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String msg = "";
		
		HttpSession session = req.getSession();
		
		//btnを取得
		req.setCharacterEncoding("utf-8");
		String btn = req.getParameter("btn");

		//DAOオブジェクトを生成
		YukyuInsert ydao = new YukyuInsert();
		//ボタンによる処理
		if (btn.equals("申請")) {
			//リクエストスコープからログインユーザーのIDを取得する
			String userId = (String) session.getAttribute("user_id");
			String yoteibi = req.getParameter("yoteibi");
			ydao.insertYukyu(userId, yoteibi);
			msg = "申請日" + yoteibi + "を申請しました";
			req.setAttribute("msg", msg);
		}

		//JSPにフォワード
		String page;
		page = "WEB-INF/view/loginJugyouin.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(page);
		rd.forward(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}
}
