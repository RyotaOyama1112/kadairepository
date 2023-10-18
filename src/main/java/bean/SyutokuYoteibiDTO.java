package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class SyutokuYoteibiDTO implements Serializable{
  private ArrayList<SyutokuYoteibiBean> list;

  public SyutokuYoteibiDTO(){
    list = new ArrayList<SyutokuYoteibiBean>();
  }
  public void add(SyutokuYoteibiBean kb){
    list.add(kb);
  }
  public SyutokuYoteibiBean get(int i){
    return list.get(i);
  }
  public int size(){
    return list.size();
  }
}

