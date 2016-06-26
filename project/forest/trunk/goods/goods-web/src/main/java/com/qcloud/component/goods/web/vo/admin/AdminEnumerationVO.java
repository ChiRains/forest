package com.qcloud.component.goods.web.vo.admin;

import java.util.List;
import com.qcloud.component.goods.web.form.ImageForm;

public class AdminEnumerationVO {
    
    private Long id;
    
    // 名称
    private String name;
    // 值
    private String value;
    
    private List<ImageForm> imageForms; 

    public AdminEnumerationVO() {
    }

    public AdminEnumerationVO(long id, String name, String value) {
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    
    public List<ImageForm> getImageForms() {
    
        return imageForms;
    }

    
    public void setImageForms(List<ImageForm> imageForms) {
    
        this.imageForms = imageForms;
    }

    
    public Long getId() {
    
        return id;
    }

    
    public void setId(Long id) {
    
        this.id = id;
    }
}
