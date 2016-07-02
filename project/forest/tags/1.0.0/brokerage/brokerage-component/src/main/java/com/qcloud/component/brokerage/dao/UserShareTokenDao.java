package com.qcloud.component.brokerage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.brokerage.model.UserShareToken;
import com.qcloud.component.brokerage.model.query.UserShareTokenQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
		
public interface UserShareTokenDao extends ISimpleDao<UserShareToken, Long> {

	public boolean add(UserShareToken userShareToken);	
	
	public UserShareToken get(Long id);

	public boolean delete(Long id);
	
	public boolean update(UserShareToken userShareToken);
	
	public List<UserShareToken> list(List<Long> idList);
	
	public Map<Long, UserShareToken> map(Set<Long> idSet);
	
	public Page<UserShareToken> page(UserShareTokenQuery query, int start, int size);

	public List<UserShareToken> listAll();
	
	UserShareToken getByUser(Long userId);
	
	UserShareToken getByToken(String token);
}
