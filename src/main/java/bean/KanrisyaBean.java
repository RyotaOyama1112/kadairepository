package bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.PositiveOrZero;

import annotations.NameValidation;

public class KanrisyaBean implements ValidatableBean, Serializable {
    @NameValidation(message = "名前は1文字以上30文字以下である必要があります")
	private String userID;

    private Date yoteibi;
    
    @NameValidation(message = "名前は1文字以上30文字以下である必要があります")
    private String name;
    
    @NameValidation(message = "部署は30文字以下である必要があります")
    private String busyo;
    
    @PositiveOrZero
    private int status_name;
    private Date sinseibi;

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setYoteibi(Date yoteibi) {
        this.yoteibi = yoteibi;
    }

    public Date getYoteibi() {
        return yoteibi;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBusyo(String busyo) {
        this.busyo = busyo;
    }

    public String getBusyo() {
        return busyo;
    }

    public void setStatus_name(int status_name) {
        this.status_name = status_name;
    }

    public int getStatus_name() {
        return status_name;
    }

    public void setSinseibi(Date sinseibi) {
        this.sinseibi = sinseibi;
    }

    public Date getSinseibi() {
        return sinseibi;
    }

    @Override
    public List<String> validate() {
        List<String> errors = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        
        for (Field field : fields) {
            if (field.isAnnotationPresent(NameValidation.class)) {
                field.setAccessible(true);
                try {
                    String value = (String) field.get(this);
                    if (value == null || value.isEmpty()) {
                        errors.add(field.getAnnotation(NameValidation.class).message());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return errors;
    }
}

