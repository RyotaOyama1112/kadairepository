package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import DAO.UserDAO;
import DAO.UserJohoDAO;
import bean.YukyuBean;

@WebServlet("/yukyu")
public class YukyuController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String msg = "";

        // フォームからの入力値を取得
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String busyo = req.getParameter("busyo");
        int kanriFlg = Integer.parseInt(req.getParameter("kanriFlg"));

        // YukyuBeanオブジェクトを生成し、入力値をセット
        YukyuBean yukyuBean = new YukyuBean();
        yukyuBean.setId(id);
        yukyuBean.setName(name);
        yukyuBean.setPassword(password);
        yukyuBean.setBusyo(busyo);
        yukyuBean.setKanriFlg(kanriFlg);

        // 入力値の検証
        List<String> errors = validateYukyuBean(yukyuBean);

        // バリデーションエラーがある場合にはエラーメッセージをセットしてフォーワードする
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("WEB-INF/view/InsertForm.jsp").forward(req, res);
            return;
        }

        // 入力値が正常な場合はDBに登録
        UserJohoDAO ydao = new UserJohoDAO();
        UserDAO ydao2 = new UserDAO();
        ydao.insert(yukyuBean.getId(), yukyuBean.getPassword(), yukyuBean.getName(), yukyuBean.getBusyo(), yukyuBean.getKanriFlg());
        ydao2.insert(yukyuBean.getId(), yukyuBean.getPassword(), yukyuBean.getKanriFlg());
        
        // 登録完了メッセージをセット
        msg = "ID" + id + "のデータを追加しました";
        req.setAttribute("msg", msg);
        
        // フォーワード
        req.getRequestDispatcher("WEB-INF/view/InsertForm.jsp").forward(req, res);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    // YukyuBeanの検証メソッド
    private List<String> validateYukyuBean(YukyuBean yukyuBean) {
        List<String> errors = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<YukyuBean>> violations = validator.validate(yukyuBean);
        for (ConstraintViolation<YukyuBean> violation : violations) {
            errors.add(violation.getMessage());
        }
        return errors;
    }
}
