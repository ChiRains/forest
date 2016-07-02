package com.qcloud.component.commoditycenter.web.form;


public class RelationForm {
    
    private Long attributeId;
    
    private String value;
    
    private String alias;
    
    private int  isCheck;
    
    private String oldAlias;
    
    private int isExtra;
    
    private int type;
    

    
    public Long getAttributeId() {
    
        return attributeId;
    }

    
    public void setAttributeId(Long attributeId) {
    
        this.attributeId = attributeId;
    }

    
    public String getValue() {
    
        return value;
    }

    
    public void setValue(String value) {
    
        this.value = value;
    }

    
    public String getAlias() {
    
        return alias;
    }

    
    public void setAlias(String alias) {
    
        this.alias = alias;
    }


    
    public int getIsCheck() {
    
        return isCheck;
    }


    
    public void setIsCheck(int isCheck) {
    
        this.isCheck = isCheck;
    }


    
    public String getOldAlias() {
    
        return oldAlias;
    }


    
    public void setOldAlias(String oldAlias) {
    
        this.oldAlias = oldAlias;
    }


    
    public int getIsExtra() {
    
        return isExtra;
    }


    
    public void setIsExtra(int isExtra) {
    
        this.isExtra = isExtra;
    }


    
    public int getType() {
    
        return type;
    }


    
    public void setType(int type) {
    
        this.type = type;
    }
}
