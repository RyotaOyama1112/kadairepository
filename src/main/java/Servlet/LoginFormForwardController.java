package Servlet; 
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginForm")

public class LoginFormForwardController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       //フォワード先の指定
       RequestDispatcher dispatcher =  request.getRequestDispatcher("/WEB-INF/view/Login.jsp");
       //フォワードの実行
        dispatcher.forward(request, response);
 }
    }S