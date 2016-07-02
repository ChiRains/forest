package com.qcloud.project.forest.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class UserdbVO {
	
	private long id;		
	
	private String name;		
	
	private String nickname;		
	
	private String password;		

	public UserdbVO(){
	
	}

	public UserdbVO(long id,String name,String nickname,String password){
		this.id = id;		
		this.name = name;		
		this.nickname = nickname;		
		this.password = password;		
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
		
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}	
		
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}	
		
}
