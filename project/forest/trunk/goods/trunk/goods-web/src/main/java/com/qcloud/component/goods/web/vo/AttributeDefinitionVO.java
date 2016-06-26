package com.qcloud.component.goods.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class AttributeDefinitionVO {
	
	private long id;		
	
	//名称
	private String name;		
	
	//编码
	private String code;		
	
	//说明
	private String remark;		
	
	//枚举定义名称
	private String enumeration;		
	
	//值
	private String value;		
	
	//类别 1属性2规格
	private String type;		
	
	//值类型：1输入框2下拉框3单选框4多选框5富文本
	private String valueType;		

	public AttributeDefinitionVO(){
	
	}

	public AttributeDefinitionVO(long id,String name,String code,String remark,String enumeration,String value,String type,String valueType){
		this.id = id;		
		this.name = name;		
		this.code = code;		
		this.remark = remark;		
		this.enumeration = enumeration;		
		this.value = value;		
		this.type = type;		
		this.valueType = valueType;		
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
		
	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}	
		
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}	
		
	public void setEnumeration(String enumeration) {
		this.enumeration = enumeration;
	}

	public String getEnumeration() {
		return enumeration;
	}	
		
	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	
		
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}	
		
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getValueType() {
		return valueType;
	}	
		
}
