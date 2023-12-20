package DAO;

import java.sql.ResultSet;

public class LoginCheck {
    public int getFlg(String id) {
        try {
            String sql = "SELECT KanriFlg FROM user情報 WHERE userID=?";
            try (ResultSet rs = DatabaseConnector.executeQuery(sql, id)) {

                if (rs.next()) {
                    int kanriFlg = rs.getInt("KanriFlg");
                    return kanriFlg;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("データベースクエリエラー", e);
        }
        return 0;
    }
}
