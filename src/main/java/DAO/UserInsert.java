package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.DatabaseConnector;

public class UserInsert {

    // 新規登録情報をuserテーブルに追加するsql
    public int insert(String id, String password, int kanriFlg) {
        String sql = "INSERT INTO user(userID, password, KanriFlg) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, password);
            pstmt.setInt(3, kanriFlg);

            return pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); 
            return 0;
        }
    }
}
