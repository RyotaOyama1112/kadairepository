package DTO;

import java.io.Serializable;
import java.util.ArrayList;

import bean.YukyuBean;

public class YukyuDTO implements Serializable {
	private ArrayList<YukyuBean> list;

	public YukyuDTO() {
		list = new ArrayList<YukyuBean>();
	}

	public void add(YukyuBean sb) {
		list.add(sb);
	}

	public YukyuBean get(int i) {
		return list.get(i);
	}

	public int size() {
		return list.size();
	}
}
