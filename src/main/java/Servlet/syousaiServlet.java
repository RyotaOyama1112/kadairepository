package Servlet;

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
		
		KanrisyaDTO kdto = (KanrisyaDTO)req.getAttribute("kdto");
		
	    String num[] = new String[kdto.size()];
	    String suuji;
	    
		for(int i = 0; i < kdto.size(); i++){
			String bangou = String.valueOf(i);
			suuji = req.getParameter(bangou);
			num[i] = suuji;
		}
		
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