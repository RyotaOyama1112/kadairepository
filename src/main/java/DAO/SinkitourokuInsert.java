// SinkitourokuInsert.java

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SinkitourokuInsert {
    // 新規登録情報をuser情報テーブルに追加するsql
    public int insert(String id, String password, String name, String busyo, int kanriFlg) {
        String sql = "INSERT INTO user情報(userID, password, Name, Busyo, KanriFlg) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, busyo);
            pstmt.setInt(5, kanriFlg);

            return pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); 
            return 0;
        }
    }
}
