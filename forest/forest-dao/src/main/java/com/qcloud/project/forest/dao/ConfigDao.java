package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.Config;
import com.qcloud.project.forest.model.query.ConfigQuery;
		
public interface ConfigDao extends ISimpleDao<Config, Long> {

	public boolean add(Config config);	
	
	public Config get(Long id);

	public boolean delete(Long id);
	
	public boolean update(Config config);
	
	public List<Config> list(List<Long> idList);
	
	public Map<Long, Config> map(Set<Long> idSet);
	
	public Page<Config> page(ConfigQuery query, int start, int size);

	public List<Config> listAll();
	
	public List<Config> listByType(Integer type);

}
