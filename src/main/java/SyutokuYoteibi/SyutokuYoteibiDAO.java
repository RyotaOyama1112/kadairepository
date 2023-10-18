package SyutokuYoteibi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import bean.SyutokuYoteibiBean;
import bean.SyutokuYoteibiDTO;

public class SyutokuYoteibiDAO {
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
  
  public SyutokuYoteibiDTO select() {
    Statement stmt = null;
    ResultSet rs = null;
    SyutokuYoteibiDTO sdto = new SyutokuYoteibiDTO();
    String sql = "SELECT yoteibi FROM 有給申請";
    try{
      connect();
      //②ステートメントを生成
      stmt = con.createStatement();
      //③SQLを実行
      rs = stmt.executeQuery(sql);
      //④検索結果の処理
      while(rs.next()){
        SyutokuYoteibiBean sb = new SyutokuYoteibiBean();
        sb.setYoteibi(rs.getDate("yoteibi"));
        sdto.add(sb);
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
    return sdto;
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

