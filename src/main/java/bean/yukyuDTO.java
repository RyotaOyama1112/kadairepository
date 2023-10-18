package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class yukyuDTO implements Serializable {
	private ArrayList<yukyuBean> list;

	public yukyuDTO() {
		list = new ArrayList<yukyuBean>();
	}

	public void add(yukyuBean sb) {
		list.add(sb);
	}

	public yukyuBean get(int i) {
		return list.get(i);
	}

	public int size() {
		return list.size();
	}
}
