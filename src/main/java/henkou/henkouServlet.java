package henkou;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanrisya.KanrisyaDAO;

@WebServlet("/henkou")
public class henkouServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		//btnを取得
		req.setCharacterEncoding("utf-8");
		
		//DAOオブジェクトを生成
		KanrisyaDAO kdao = new KanrisyaDAO();
		
		//KanrisyaBean kb = kdto.get();

		int kanriFlg = Integer.valueOf(req.getParameter("status"));
		//kdao.update(kanriFlg);


		//JSPにフォワード
		String page;
		page = "loginKanrisya.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(page);
		rd.forward(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}
}
