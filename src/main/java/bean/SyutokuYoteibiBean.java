package bean;

import java.io.Serializable;
import java.sql.Date;

public class SyutokuYoteibiBean implements Serializable{
  private String user;
  private Date yoteibi ;
  
  public void setUser(String user){
    this.user = user;
  }
  public void setYoteibi(Date yoteibi){
    this.yoteibi = yoteibi;
  }
  public String getUser(){
    return user;
  }
  public Date getYoteibi(){
    return yoteibi;
  }
}

