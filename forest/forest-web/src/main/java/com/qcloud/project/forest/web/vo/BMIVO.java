package com.qcloud.project.forest.web.vo;

import java.util.List;

public class BMIVO {
	
	//id
	private long id;		
		
	//名称
	private String name;		
	
	//描述
	private String description;				
	
	// BMI
 	private double bmi;
	
	//范围
	private List<RangeList> rangeData ; 

	

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBmi() {
		return bmi;
	}

	public void setBmi(double bmi) {
		this.bmi = bmi;
	}

	public List<RangeList> getRangeData() {
		return rangeData;
	}

	public void setRangeData(List<RangeList> rangeData) {
		this.rangeData = rangeData;
	}

	
		
}
