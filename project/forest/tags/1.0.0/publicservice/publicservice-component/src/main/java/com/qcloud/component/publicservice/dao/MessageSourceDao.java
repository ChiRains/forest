package com.qcloud.component.publicservice.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.publicservice.model.MessageSource;
import com.qcloud.component.publicservice.model.query.MessageSourceQuery;
		
public interface MessageSourceDao extends ISimpleDao<MessageSource, Long> {

	public boolean add(MessageSource messageSource);	
	
	public MessageSource get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MessageSource messageSource);
	
	public List<MessageSource> list(List<Long> idList);
	
	public Map<Long, MessageSource> map(Set<Long> idSet);
	
	public Page<MessageSource> page(MessageSourceQuery query, int start, int size);

	public List<MessageSource> listAll();
	
}
