package DAO;

import java.sql.ResultSet;

public class LoginCheck {
    public int getFlg(String id) {
        try {
            String sql = "SELECT KanriFlg FROM user情報 WHERE userID=?";
            ResultSet rs = DatabaseConnector.executeQuery(sql, id);

            if (rs.next()) {
                return rs.getInt("KanriFlg");
            }
        } catch (Exception e) {
            throw new RuntimeException("データベースクエリエラー", e);
        }
        return 0; // 
    }
}
