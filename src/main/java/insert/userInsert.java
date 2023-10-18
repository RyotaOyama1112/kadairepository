package insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class userInsert {
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

	//新規登録情報をuserテーブルに追加するsql
	public int insert(String id, String password, int kanriFlg) {
		String sql = "INSERT INTO user(userID, password, KanriFlg) VALUES ('" + id + "', '" + password
				+ "', " + kanriFlg + ")";
		return executeSql(sql);
	}

	//sql文を受けて動かす
	public int executeSql(String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connect();
			//ステートメントを生成
			stmt = con.createStatement();
			//SQLを実行
			result = stmt.executeUpdate(sql);
			
			//insert
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
		return result;
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