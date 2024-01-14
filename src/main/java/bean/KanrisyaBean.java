package bean;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class KanrisyaBean implements Serializable{
  private String userID;
  
  private Date yoteibi;
  
  @Size(min = 1, max = 30, message = "名前は1文字以上30文字以下である必要があります")
  private String name;
  
  @Size(max = 30, message = "部署は30文字以下である必要があります")
  private String busyo;
  
  @PositiveOrZero
  private int status_name;
  
  private Date sinseibi;
  
  public void setUserid(String userID){
	    this.userID = userID;
	  }
  public void setSinseibi(Date sinseibi){
	    this.sinseibi = sinseibi;
	  }
  public void setYoteibi(Date yoteibi){
    this.yoteibi = yoteibi;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setBusyo(String busyo){
    this.busyo = busyo;
  }
  public void setStatus_name(int setStatus_name){
	    this.status_name = setStatus_name;
	  }
  public String getUserid(){
	    return userID;
	  }
  public Date getSinseibi(){
	    return sinseibi;
	  }
  public Date getYoteibi(){
    return yoteibi;
  }
  public String getName(){
    return name;
  }
  public String getBusyo(){
    return busyo;
  }
  public int getStatus_name(){
	    return status_name;
	  }
}

