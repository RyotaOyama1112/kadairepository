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

//新規登録情報をuser情報テーブルに追加するsql
//旧SinkitourokuInsert
//トランザクション実装済
public int insert(String id, String password, String name, String busyo, int kanriFlg) {
    String sql = "INSERT INTO user情報(userID, password, Name, Busyo, KanriFlg) VALUES (?, ?, ?, ?, ?)";
    int result = 0;
    Connection con = null;
    PreparedStatement pstmt = null;

    try {
        con = DatabaseConnector.getConnection();
        con.setAutoCommit(false); // トランザクションの開始

        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, password);
        pstmt.setString(3, name);
        pstmt.setString(4, busyo);
        pstmt.setInt(5, kanriFlg);

        result = pstmt.executeUpdate();

        con.commit(); // トランザクションのコミット
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        try {
            if (con != null) {
                con.rollback(); // トランザクションのロールバック
            }
        } catch (SQLException rollbackException) {
            rollbackException.printStackTrace();
        }
    } finally {
        try {
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
}}