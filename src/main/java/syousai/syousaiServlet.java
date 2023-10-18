package syousai;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.KanrisyaDTO;

@WebServlet("/Syousai")
public class syousaiServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String suuji = req.getParameter("suuji");
		//ここが問題
		
		
		//DAOオブジェクトを生成
		syousaiDAO sdao = new syousaiDAO();
		//ボタンによる処理
	
		KanrisyaDTO kdto = sdao.select();
		//btn2 = Integer.valueOf(req.getParameter(btn));
		
		req.setAttribute("kdto", kdto);
		//JSPにフォワード
		String page;
		page = "syousai.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(page);
		rd.forward(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}
}