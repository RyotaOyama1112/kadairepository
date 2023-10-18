package bean;

import java.io.Serializable;

public class yukyuBean implements Serializable {
	private String id;
	private String name;
	private String password;	
	private int kanriFlg;
	private String busyo;	

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setKanriFlg(int kanriFlg) {
		this.kanriFlg = kanriFlg;
	}
	
	public void setBusyo(String busyo) {
		this.busyo = busyo;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
	public int getKanriFlg() {
		return kanriFlg;
	}
	public String getBusyo() {
		return busyo;
	}	
}
