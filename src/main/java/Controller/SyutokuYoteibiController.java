package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.YukyuSinseiDAO;
import DTO.SyutokuYoteibiDTO;

@WebServlet("/SyutokuYoteibi")
public class SyutokuYoteibiController extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    //DAOオブジェクトを生成
	  YukyuSinseiDAO sdao = new YukyuSinseiDAO();
    //全件検索した結果をDTOオブジェクトとして取得
    SyutokuYoteibiDTO sdto = sdao.select();
    //検索結果をリクエストスコープに格納
    req.setAttribute("sdto", sdto);
    //JSPにフォワード
    RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/LoginJugyouin.jsp");
    rd.forward(req, res);
  }

  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    doPost(req, res);
  }
}

