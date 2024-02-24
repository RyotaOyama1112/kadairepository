package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJohoDAO {
    public static ResultSet executeQuery(String sql, String... params) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            pstmt.setString(i + 1, params[i]);
        }

        return pstmt.executeQuery();
    }

    //LoginContoller行
    public static ResultSet executeLoginQuery(String userId, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT userID, Busyo, KanriFlg FROM user情報 WHERE userID=? AND password=?";
        return executeQuery(sql, userId, password);
    }
    
    //旧LoginCheckクラス
    public int getFlg(String id) {
        try {
            String sql = "SELECT KanriFlg FROM user情報 WHERE userID=?";
            try (ResultSet rs = executeQuery(sql, id)) {

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