package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.DatabaseConnector;

public class YukyuInsert {

    // 従業員画面から申請日を追加するsql
    public int insertYukyu(String userId, String yoteibi) {
        String sql = "INSERT INTO 有給申請(userID, yoteibi, status_name) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, yoteibi);
            pstmt.setInt(3, 1);

            return pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); 
            return 0;
        }
    }
}
