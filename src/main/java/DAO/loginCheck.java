package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginCheck {
	private final String URL = "jdbc:mysql://localhost/yukyudb";
	private final String USER = "root";
	private final String PASS = "4649";
	private Connection con = null;

	public void connect() { //接続
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getFlg(String id) {
		Statement stmt = null;
		ResultSet rs = null;
		int kanriFlg = 0;
		String sql = "select KanriFlg from user情報 where userID='"+ id +"'";
		try {
			connect();
			//ステートメントを生成
			stmt = con.createStatement();
			//SQLを実行
			rs = stmt.executeQuery(sql);
			//検索結果の処理
			while (rs.next()) {
				int kanriFlg2 = rs.getInt("KanriFlg");
				kanriFlg = kanriFlg2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		disconnect();
		return kanriFlg;
	}

	public void disconnect() {
		try {
			//DBを切断
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}