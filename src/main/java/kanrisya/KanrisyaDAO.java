package kanrisya;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import bean.KanrisyaBean;
import bean.KanrisyaDTO;

public class KanrisyaDAO {
  private final String URL = "jdbc:mysql://localhost/yukyudb";
  private final String USER = "root";
  private final String PASS = "4649";
  private Connection con = null;

  public void connect(){
    try{
      //①DBに接続
      con = DriverManager.getConnection(URL, USER, PASS);
    } catch(Exception e){
      e.printStackTrace();
    }
  }
  
  public KanrisyaDTO selectYukyu(String yoteibi,String name,String busyo,int kanriFlg) {
    Statement stmt = null;
    ResultSet rs = null;
    KanrisyaDTO kdto = new KanrisyaDTO();
    String sql = "SELECT yoteibi,userID FROM 有給申請 where yoteibi='" + yoteibi + "'or name='" + name + "'or busyo='" + busyo + "'or status_name=" + kanriFlg;
    try{
      connect();
      //②ステートメントを生成
      stmt = con.createStatement();
      //③SQLを実行
      rs = stmt.executeQuery(sql);
      //④検索結果の処理
      while(rs.next()){
    	KanrisyaBean kb = new KanrisyaBean();
        kb.setYoteibi(rs.getDate("yoteibi"));
        kdto.add(kb);
      }
    } catch(Exception e){
      e.printStackTrace();
    } finally {
      try{
        if(rs != null) rs.close();
        if(stmt != null) stmt.close();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    disconnect();
    return kdto;
  }
  
  public int update(int kanriFlg) {
	    String sql = "UPDATE 有給申請 SET status_name = " + kanriFlg; 
	    return executeSql(sql);
	  }
	 
	  
	  public int executeSql(String sql) {
	    Statement stmt = null;
	    ResultSet rs = null;
	    int result = 0;
	    try{
	      connect();
	      //②ステートメントを生成
	      stmt = con.createStatement();
	      //③SQLを実行
	      result = stmt.executeUpdate(sql);
	    } catch(Exception e){
	      e.printStackTrace();
	    } finally {
	      try{
	        if(rs != null) rs.close();
	        if(stmt != null) stmt.close();
	      } catch(Exception e){
	        e.printStackTrace();
	      }
	    }
	    disconnect();
	    return result;
	  }

  public void disconnect(){
    try{
      //⑤DBを切断
      if(con != null) con.close();
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}

