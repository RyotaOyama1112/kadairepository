package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    // 新規登録情報をuserテーブルに追加するsql
    public int insert(String id, String password, int kanriFlg) {
        String sql = "INSERT INTO user(userID, password, KanriFlg) VALUES (?, ?, ?)";
        int result = 0;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // データベースへの接続
            con = DatabaseConnector.getConnection();
            // トランザクションの開始
            con.setAutoCommit(false);

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            pstmt.setInt(3, kanriFlg);

            result = pstmt.executeUpdate();

            // トランザクションのコミット
            con.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                // トランザクションのロールバック
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        } finally {
            try {
                // トランザクションの終了
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }

        return result;
    }
}
