package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.SyutokuYoteibiBean;
import bean.SyutokuYoteibiDTO;

public class SyutokuYoteibiDAO {
    private Connection con = null;

    public void connect() {
        try {
            // DatabaseConnectorを使用してDBに接続
            con = DatabaseConnector.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SyutokuYoteibiDTO select() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SyutokuYoteibiDTO sdto = new SyutokuYoteibiDTO();
        String sql = "SELECT yoteibi FROM 有給申請";
        try {
            connect();
            // プリペアドステートメントを生成
            pstmt = con.prepareStatement(sql);
            // SQLを実行
            rs = pstmt.executeQuery();
            // 検索結果の処理
            while (rs.next()) {
                SyutokuYoteibiBean sb = new SyutokuYoteibiBean();
                sb.setYoteibi(rs.getDate("yoteibi"));
                sdto.add(sb);
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
        return sdto;
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
