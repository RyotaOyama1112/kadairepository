package Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserJohoDAO;
import DAO.YukyuSinseiDAO;
import DTO.KanrisyaDTO;
import DTO.SyutokuYoteibiDTO;

@WebServlet("/LoginServlet")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("user_id");
        String password = request.getParameter("password");
        
        if (userId == null || userId.isEmpty() || password == null || password.isEmpty()) {
            // ユーザーIDまたはパスワードが未入力の場合
            request.setAttribute("loginFailure", "ユーザーIDとパスワードを入力してください");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/Login.jsp");
            rd.forward(request, response);
            return;
        }

        String path = "";

        try {
            try (ResultSet res = UserJohoDAO.executeLoginQuery(userId, password)) {

                if (res.next()) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user_id", res.getString("userID"));
                    session.setAttribute("user_busyo", res.getString("Busyo"));
                    session.setAttribute("user_KanriFlg", res.getInt("KanriFlg"));

                    UserJohoDAO lDAO = new UserJohoDAO();
                    int kanriFlg = lDAO.getFlg(userId); 

                    if (kanriFlg == 1) {
                        // 全件検索を実行して結果を取得
                        YukyuSinseiDAO sdao = new YukyuSinseiDAO();
                        SyutokuYoteibiDTO sdto = sdao.select();
                        request.setAttribute("sdto", sdto);
                        path = "WEB-INF/view/loginJugyouin.jsp";
                    } else if (kanriFlg == 3) {
                    	// 全件検索を実行して結果を取得
                    	YukyuSinseiDAO sdao = new YukyuSinseiDAO();
                    	KanrisyaDTO kdto = sdao.select2();
                    	request.setAttribute("kdto", kdto);
                        path = "WEB-INF/view/loginKanrisya.jsp";
                    }
                } else {
                    request.setAttribute("loginFailure", "ログインに失敗しました");
                    path = "WEB-INF/view/Login.jsp";
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd2 = request.getRequestDispatcher(path);
        rd2.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/Login.jsp");
        rd.forward(request, response);
    }
}
