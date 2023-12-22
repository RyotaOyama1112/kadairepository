package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.KanrisyaDAO;
import bean.KanrisyaDTO;

@WebServlet("/KanrisyaServlet")
public class KanrisyaServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String msg = "";
		String yoteibi = null;
		String name = null;
		String busyo = null;
		int kanriFlg = 0;
		//int num = 0;

		//btnを取得
		req.setCharacterEncoding("utf-8");
		String btn = req.getParameter("btn");

		//DAOオブジェクトを生成
		KanrisyaDAO kdao = new KanrisyaDAO();
		//ボタンによる処理
		if (btn.equals("検索")) {
			HttpSession session = req.getSession(true);
			//リクエストスコープからログインユーザーのIDを取得する
			 yoteibi = req.getParameter("yoteibi");
			 name = req.getParameter("name");
			 busyo = req.getParameter("busyo");
			 kanriFlg = Integer.valueOf(req.getParameter("status"));
			KanrisyaDTO kdto = kdao.selectYukyu(yoteibi,name,busyo,kanriFlg);
			msg = "検索しました";
			session.setAttribute("KSkdto", kdto);
			req.setAttribute("kdto", kdto);
			req.setAttribute("msg", msg);
		} 

		//JSPにフォワード
		String page;
		page = "WEB-INF/view/loginKanrisya.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(page);
		rd.forward(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}
}
