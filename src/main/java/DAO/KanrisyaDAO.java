package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.KanrisyaBean;
import bean.KanrisyaDTO;

public class KanrisyaDAO {
    private Connection con = null;

    public void connect() {
        try {
            // DatabaseConnectorを使用してDBに接続
            con = DatabaseConnector.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public KanrisyaDTO selectYukyu(String yoteibi, String name, String busyo, int kanriFlg) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        KanrisyaDTO kdto = new KanrisyaDTO();
        String sql = "SELECT yoteibi, userID FROM 有給申請 WHERE yoteibi=? OR name=? OR busyo=? OR status_name=?";
        try {
            connect();
            // プリペアドステートメントを生成
            pstmt = con.prepareStatement(sql);
            // パラメータを設定
            pstmt.setString(1, yoteibi);
            pstmt.setString(2, name);
            pstmt.setString(3, busyo);
            pstmt.setInt(4, kanriFlg);
            // SQLを実行
            rs = pstmt.executeQuery();
            // 検索結果の処理
            while (rs.next()) {
                KanrisyaBean kb = new KanrisyaBean();
                kb.setYoteibi(rs.getDate("yoteibi"));
                kb.setUserid(rs.getString("userID"));
                kdto.add(kb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        disconnect();
        return kdto;
    }

    public int executeSql(String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            connect();
            // ステートメントを生成
            stmt = con.createStatement();
            // SQLを実行
            result = stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        disconnect();
        return result;
    }

    public void updateStatus(String userId, String yoteibi, int newStatus) {
        PreparedStatement pstmt = null;
        String sql = "UPDATE 有給申請 SET status_name = ? WHERE userID = ? AND yoteibi = ?";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, newStatus);
            pstmt.setString(2, userId);
            pstmt.setString(3, yoteibi);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    public void disconnect() {
        try {
            // DBを切断
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
