package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class KanrisyaDTO implements Serializable{
  private ArrayList<KanrisyaBean> list;

  public KanrisyaDTO(){
    list = new ArrayList<KanrisyaBean>();
  }
  public void add(KanrisyaBean sb){
    list.add(sb);
  }
  public KanrisyaBean get(int i){
    return list.get(i);
  }
  public int size(){
    return list.size();
  }
}

