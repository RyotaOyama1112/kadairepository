package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DTO.KanrisyaDTO;
import DTO.SyutokuYoteibiDTO;
import bean.KanrisyaBean;
import bean.SyutokuYoteibiBean;

public class YukyuSinseiDAO {
    private Connection con = null;

    public void connect() {
        try {
            // DatabaseConnectorを使用してDBに接続
            con = DatabaseConnector.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ここから旧KanrisyaDAO
    public KanrisyaDTO select(String yoteibi, String name, String busyo, int kanriFlg) {
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
                kb.setUserID(rs.getString("userID"));
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

  //トランザクション実装済
    public void updateStatus(String userId, String yoteibi, int newStatus) {
        PreparedStatement pstmt = null;
        String sql = "UPDATE 有給申請 SET status_name = ? WHERE userID = ? AND yoteibi = ?";

        try {
            connect();
            con.setAutoCommit(false); // トランザクションの開始

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, newStatus);
            pstmt.setString(2, userId);
            pstmt.setString(3, yoteibi);
            pstmt.executeUpdate();

            con.commit(); // トランザクションのコミット
        } catch (SQLException e) {
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
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }
        disconnect();
    }

    public void disconnect() {
        try {
            // DBを切断
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ここまで旧KanrisyaDAO

    public static void getUserInfo(Connection connection, HttpServletRequest request, String userId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM 有給申請 WHERE userID = ?")) {
            statement.setString(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // データベースから取得した値をセッションにセット
                    HttpSession session = request.getSession();
                    session.setAttribute("userid", resultSet.getString("userID"));
                    session.setAttribute("name", resultSet.getString("Name"));
                    session.setAttribute("busyo", resultSet.getString("Busyo"));
                    session.setAttribute("sinseibi", resultSet.getDate("Sinseibi"));
                    session.setAttribute("yoteibi", resultSet.getDate("yoteibi"));
                    session.setAttribute("status_name", resultSet.getInt("status_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 従業員画面から申請日を追加するsql
    //旧YukyuInsert
    public int insert(String userId, String yoteibi) {
        String sql = "INSERT INTO 有給申請(userID, yoteibi, status_name) VALUES (?, ?, ?)";
        int result = 0;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DatabaseConnector.getConnection();
            con.setAutoCommit(false); // トランザクションの開始

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, yoteibi);
            pstmt.setInt(3, 1);

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
    }

    public SyutokuYoteibiDTO select() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SyutokuYoteibiDTO sdto = new SyutokuYoteibiDTO();
        String sql = "SELECT * FROM 有給申請";
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
    public KanrisyaDTO select2() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        KanrisyaDTO kdto = new KanrisyaDTO();
        String sql = "SELECT * FROM 有給申請";
        try {
            connect();
            // プリペアドステートメントを生成
            pstmt = con.prepareStatement(sql);
            // SQLを実行
            rs = pstmt.executeQuery();
            // 検索結果の処理
            while (rs.next()) {
                KanrisyaBean kb = new KanrisyaBean();
                kb.setYoteibi(rs.getDate("yoteibi"));
                kb.setUserID(rs.getString("userID"));
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
}
