package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SyutokuYoteibiDAO;
import bean.SyutokuYoteibiDTO;

@WebServlet("/SyutokuYoteibi")
public class SyutokuYoteibiServlet extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    //DAOオブジェクトを生成
    SyutokuYoteibiDAO sdao = new SyutokuYoteibiDAO();
    //全件検索した結果をDTOオブジェクトとして取得
    SyutokuYoteibiDTO sdto = sdao.select();
    //検索結果をリクエストスコープに格納
    req.setAttribute("sdto", sdto);
    //JSPにフォワード
    RequestDispatcher rd = req.getRequestDispatcher("/loginJugyouin.jsp");
    rd.forward(req, res);
  }

  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    doPost(req, res);
  }
}

