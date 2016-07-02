package com.qcloud.component.goods.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class AttributeDefinitionVO {
	
	private long id;		
	
	//名称
	private String name;		
	
	//枚举定义名称
	private String enumeration;		
	
	private String value;

    private String valueType;   // 值类型：1输入框2下拉框3单选框4多选框5富文本

    private String type;       // 类别 1属性2规格

	public AttributeDefinitionVO(){
	
	}

	public AttributeDefinitionVO(long id,String name,String enumeration){
		this.id = id;		
		this.name = name;		
		this.enumeration = enumeration;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setEnumeration(String enumeration) {
		this.enumeration = enumeration;
	}

	public String getEnumeration() {
		return enumeration;
	}

    
    public String getValue() {
    
        return value;
    }

    
    public void setValue(String value) {
    
        this.value = value;
    }

    
    public String getValueType() {
    
        return valueType;
    }

    
    public void setValueType(String valueType) {
    
        this.valueType = valueType;
    }

    
    public String getType() {
    
        return type;
    }

    
    public void setType(String type) {
    
        this.type = type;
    }	
		
}
