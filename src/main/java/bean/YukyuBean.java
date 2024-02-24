package bean;

import java.io.Serializable;

import javax.validation.constraints.PositiveOrZero;

import annotations.NameValidation;

public class YukyuBean implements Serializable {
	private String id;
	
	@NameValidation(message = "名前は1文字以上30文字以下である必要があります")
	private String name;
	
	@NameValidation(message = "パスワードは1文字以上30文字以下である必要があります")
	private String password;	
	
	@PositiveOrZero
	private int kanriFlg;
	
	@NameValidation(message = "部署は1文字以上30文字以下である必要があります")
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
