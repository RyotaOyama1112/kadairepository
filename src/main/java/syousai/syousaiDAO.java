package syousai;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import bean.KanrisyaBean;
import bean.KanrisyaDTO;

public class syousaiDAO {
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
  
  public KanrisyaDTO select() {
    Statement stmt = null;
    ResultSet rs = null;
    KanrisyaDTO kdto = new KanrisyaDTO();
    String sql = "SELECT * FROM 有給申請";
    try{
      connect();
      //②ステートメントを生成
      stmt = con.createStatement();
      //③SQLを実行
      rs = stmt.executeQuery(sql);
      //④検索結果の処理
      while(rs.next()){
        KanrisyaBean sb = new KanrisyaBean();
        sb.setName(rs.getString("name"));
        sb.setBusyo(rs.getString("busyo"));
        sb.setYoteibi(rs.getDate("yoteibi"));
        kdto.add(sb);
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

  public void disconnect(){
    try{
      //⑤DBを切断
      if(con != null) con.close();
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}

