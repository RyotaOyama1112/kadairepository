package bean;

import java.io.Serializable;
import java.sql.Date;

public class SyutokuYoteibiBean implements Serializable{
  private String userID;
  
  private Date yoteibi ;
  
  public void setUserID(String userID){
    this.userID = userID;
  }
  public void setYoteibi(Date yoteibi){
    this.yoteibi = yoteibi;
  }
  public String getUserID(){
    return userID;
  }
  public Date getYoteibi(){
    return yoteibi;
  }
}

