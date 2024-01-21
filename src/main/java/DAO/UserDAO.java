package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    // 新規登録情報をuserテーブルに追加するsql
	//トランザクション実装済
    public int insert(String id, String password, int kanriFlg) {
        String sql = "INSERT INTO user(userID, password, KanriFlg) VALUES (?, ?, ?)";
        int result = 0;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // データベースへの接続
            con = DatabaseConnector.getConnection();

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            pstmt.setInt(3, kanriFlg);

            result = pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }

        return result;
    }
}
