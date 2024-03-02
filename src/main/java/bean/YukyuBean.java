package bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.PositiveOrZero;

import annotations.NameValidation;

public class YukyuBean implements ValidatableBean {
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


    @Override
    public List<String> validate() {
        List<String> errors = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        
        for (Field field : fields) {
            if (field.isAnnotationPresent(NameValidation.class)) {
                field.setAccessible(true);
                try {
                    String value = (String) field.get(this);
                    NameValidation annotation = field.getAnnotation(NameValidation.class);
                    if (value == null || value.isEmpty() || value.length() > 30) {
                        errors.add(annotation.message());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return errors;
    }
}
