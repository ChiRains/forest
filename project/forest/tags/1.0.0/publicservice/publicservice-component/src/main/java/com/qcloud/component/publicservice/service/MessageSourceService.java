package com.qcloud.component.publicservice.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.publicservice.model.MessageSource;
import com.qcloud.component.publicservice.model.query.MessageSourceQuery;

public interface MessageSourceService {
	
	public boolean add(MessageSource messageSource);	
	
	public MessageSource get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MessageSource messageSource);

	public Page<MessageSource> page(MessageSourceQuery query, int start, int count);
	
	public List<MessageSource> listAll();
}

