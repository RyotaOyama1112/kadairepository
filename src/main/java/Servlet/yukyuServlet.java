package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SinkitourokuInsert;
import DAO.UserInsert;

@WebServlet("/yukyu")
public class yukyuServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String msg = "";

		//btnを取得
		req.setCharacterEncoding("utf-8");
		String btn = req.getParameter("btn");

		//DAOオブジェクトを生成
		SinkitourokuInsert ydao = new SinkitourokuInsert();
		UserInsert ydao2 = new UserInsert();
		
		//ボタンによる処理
		if (btn.equals("登録")) {
			String id = req.getParameter("id");
			String password = req.getParameter("password");
			String name = req.getParameter("name");
			String busyo = req.getParameter("busyo");
			int kanriFlg = Integer.valueOf(req.getParameter("kanriFlg"));
			ydao.insert(id, password, name, busyo, kanriFlg);
			ydao2.insert(id, password,kanriFlg);
			msg = "ID" + id + "のデータを追加しました";
			req.setAttribute("msg", msg);
		}

		//JSPにフォワード
		String nextPage;
		nextPage = "WEB-INF/view/insertForm.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(nextPage);
		rd.forward(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}
}
