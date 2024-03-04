package bean;

import java.io.Serializable;
import java.sql.Date;

public class SyutokuYoteibiBean implements Serializable{
  private String userID;
  
  private String Name;
  
  private String Busyo;
  
  private Date yoteibi ;
  
  public void setUserID(String userID){
    this.userID = userID;
  }
  public void setName(String Name){
	    this.Name = Name;
  }
  public void setBusyo(String Busyo){
	    this.Busyo = Busyo;
  }
  public void setYoteibi(Date yoteibi){
    this.yoteibi = yoteibi;
  }
  
  public String getUserID(){
    return userID;
  }
  public String getName(){
	    return Name;
  }
  public String getBusyo(){
	    return Busyo;
  }
  public Date getYoteibi(){
    return yoteibi;
  }
}

