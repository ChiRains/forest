package com.qcloud.component.brokerage.service;

import java.util.List;
import com.qcloud.component.brokerage.model.UserShareToken;
import com.qcloud.component.brokerage.model.query.UserShareTokenQuery;
import com.qcloud.pirates.data.Page;

public interface UserShareTokenService {
	
	public boolean add(UserShareToken userShareToken);	
	
	public UserShareToken get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(UserShareToken userShareToken);

	public Page<UserShareToken> page(UserShareTokenQuery query, int start, int count);
	
	public List<UserShareToken> listAll();
	
	//这个方法保证肯定能获取到对象
	public UserShareToken getByUser(Long userId);
	
	// token是使用getByUser获取的,则能获取到对象
	public UserShareToken getByToken(String token);
}

