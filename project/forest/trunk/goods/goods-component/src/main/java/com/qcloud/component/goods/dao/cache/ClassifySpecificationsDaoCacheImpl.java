package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.ClassifySpecificationsDao;
import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.model.query.ClassifySpecificationsQuery;

@Repository
public class ClassifySpecificationsDaoCacheImpl implements ClassifySpecificationsDao {
	
	@Autowired
	private ClassifySpecificationsDao classifySpecificationsDaoMysqlImpl;
	
	@Autowired
	private ClassifySpecificationsDao classifySpecificationsDaoRedisImpl;

	@Override
	public boolean add(ClassifySpecifications classifySpecifications) {
		return classifySpecificationsDaoMysqlImpl.add(classifySpecifications);
	}

	@Override
	public ClassifySpecifications get(Long id) {
		return CacheLoader.get(classifySpecificationsDaoRedisImpl, classifySpecificationsDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return classifySpecificationsDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(ClassifySpecifications classifySpecifications){
		return classifySpecificationsDaoMysqlImpl.update(classifySpecifications);
	}
	
	@Override
	public List<ClassifySpecifications> list(List<Long> idList) {
		return CacheLoader.list(classifySpecificationsDaoRedisImpl, classifySpecificationsDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, ClassifySpecifications> map(Set<Long> idSet){
		return CacheLoader.map(classifySpecificationsDaoRedisImpl, classifySpecificationsDaoMysqlImpl, idSet);
	}

	@Override
	public Page<ClassifySpecifications> page(int start, int count){
		return classifySpecificationsDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<ClassifySpecifications> page(ClassifySpecificationsQuery query, int start, int count){
		return classifySpecificationsDaoMysqlImpl.page(query, start, count);
	}
	
	public List<ClassifySpecifications> listAll(){
		return classifySpecificationsDaoMysqlImpl.listAll();
	}
}

