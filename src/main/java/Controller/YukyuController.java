package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import DAO.UserJohoDAO;
import bean.YukyuBean;
import service.Transaction;

@WebServlet("/yukyu")
public class YukyuController extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String msg = "";

        // btnを取得
        req.setCharacterEncoding("utf-8");
        String btn = req.getParameter("btn");

        // DAOオブジェクトを生成
        UserJohoDAO ydao = new UserJohoDAO();
        UserDAO ydao2 = new UserDAO();

        // ボタンによる処理
        if (btn.equals("登録")) {
            String id = req.getParameter("id");
            String password = req.getParameter("password");
            String name = req.getParameter("name");
            String busyo = req.getParameter("busyo");
            int kanriFlg = Integer.valueOf(req.getParameter("kanriFlg"));
            
            YukyuBean yukyuBean = new YukyuBean();
            yukyuBean.setId(req.getParameter("id"));
            yukyuBean.setName(req.getParameter("name"));
            yukyuBean.setPassword(req.getParameter("password"));
            yukyuBean.setBusyo(req.getParameter("busyo"));

            // バリデーションを実行
            List<String> errors = yukyuBean.validate();
            if (errors.isEmpty()) {
            // トランザクション内での処理
            Transaction.performTransaction(con -> {
                ydao.insert(id, password, name, busyo, kanriFlg);
                ydao2.insert(id, password, kanriFlg);
            });
            }else {
                // バリデーションエラーがある場合の処理
                req.setAttribute("errors", errors);
            }

            msg = "ID" + id + "のデータを追加しました";
            req.setAttribute("msg", msg);
        }

        // JSPにフォワード
        String nextPage;
        nextPage = "WEB-INF/view/InsertForm.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(nextPage);
        rd.forward(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doPost(req, res);
    }
}
