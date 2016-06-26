package com.qcloud.component.goods.web.vo.admin;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.goods.web.form.RelationForm;
import com.qcloud.component.publicdata.KeyValueVO;

public class AdminAttributeVO {

    private long             id;

    private String           name;

    // 1 输入框;2 下拉
    private int              type = 1;

    // 值
    private String           value;
    
    private List<RelationForm> relationForms;
    


    private List<KeyValueVO> list = new ArrayList<KeyValueVO>();

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {

        this.value = value;
    }

    public List<KeyValueVO> getList() {

        return list;
    }

    public void setList(List<KeyValueVO> list) {

        this.list = list;
    }

    
    public List<RelationForm> getRelationForms() {
    
        return relationForms;
    }

    
    public void setRelationForms(List<RelationForm> relationForms) {
    
        this.relationForms = relationForms;
    }

}
