package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ConfigDao;
import com.qcloud.project.forest.model.Config;
import com.qcloud.project.forest.model.query.ConfigQuery;

@Repository
public class ConfigDaoCacheImpl implements ConfigDao {
	
	@Autowired
	private ConfigDao configDaoMysqlImpl;
	
	@Autowired
	private ConfigDao configDaoRedisImpl;

	@Override
	public boolean add(Config config) {
		return configDaoMysqlImpl.add(config);
	}

	@Override
	public Config get(Long id) {
		return CacheLoader.get(configDaoRedisImpl, configDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return configDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(Config config){
		return configDaoMysqlImpl.update(config);
	}
	
	@Override
	public List<Config> list(List<Long> idList) {
		return CacheLoader.list(configDaoRedisImpl, configDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, Config> map(Set<Long> idSet){
		return CacheLoader.map(configDaoRedisImpl, configDaoMysqlImpl, idSet);
	}
			
		public List<Config> listByType(Integer type){
			return configDaoMysqlImpl.listByType(type);
		}
	
	@Override
	public Page<Config> page(int start, int count){
		return configDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<Config> page(ConfigQuery query, int start, int count){
		return configDaoMysqlImpl.page(query, start, count);
	}
	
	public List<Config> listAll(){
		return configDaoMysqlImpl.listAll();
	}
}

