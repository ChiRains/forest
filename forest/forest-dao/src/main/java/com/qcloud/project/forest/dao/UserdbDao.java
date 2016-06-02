package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.Userdb;
import com.qcloud.project.forest.model.query.UserdbQuery;
		
public interface UserdbDao extends ISimpleDao<Userdb, Long> {

	public boolean add(Userdb userdb);	
	
	public Userdb get(Long id);

	public boolean delete(Long id);
	
	public boolean update(Userdb userdb);
	
	public List<Userdb> list(List<Long> idList);
	
	public Map<Long, Userdb> map(Set<Long> idSet);
	
	public Page<Userdb> page(UserdbQuery query, int start, int size);

	public List<Userdb> listAll();
	
}
