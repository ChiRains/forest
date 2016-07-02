package com.qcloud.component.goods.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.goods.dao.ClassifyAttributeDao;
import com.qcloud.component.goods.model.ClassifyAttribute;
import com.qcloud.component.goods.model.query.ClassifyAttributeQuery;
		
@Repository
public class ClassifyAttributeDaoMysqlImpl implements ClassifyAttributeDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(ClassifyAttribute classifyAttribute) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.ClassifyAttributeMapper.insert", classifyAttribute) == 1;
	}	
	
	@Override
	public ClassifyAttribute get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.ClassifyAttributeMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.ClassifyAttributeMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(ClassifyAttribute classifyAttribute){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.ClassifyAttributeMapper.update", classifyAttribute) > 0;
	}
	
	@Override
	public List<ClassifyAttribute> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ClassifyAttribute> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ClassifyAttribute> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ClassifyAttribute> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.ClassifyAttributeMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.ClassifyAttributeMapper.count4page",
				param);
		Page<ClassifyAttribute> page = new Page<ClassifyAttribute>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<ClassifyAttribute> page(ClassifyAttributeQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ClassifyAttribute> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.ClassifyAttributeMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.ClassifyAttributeMapper.count4query",
				param);
		Page<ClassifyAttribute> page = new Page<ClassifyAttribute>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<ClassifyAttribute> listAll(){	
		List<ClassifyAttribute> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.ClassifyAttributeMapper.listAll");
		return list;
	}

    @Override
    public ClassifyAttribute get(Long classifyId, Long attributeId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("classifyId", classifyId);
        param.put("attributeId", attributeId);
        
        return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.ClassifyAttributeMapper.getByClassifyAndAttribute", param);
    }

    @Override
    public List<ClassifyAttribute> listByClassify(Long classifyId) {
        List<ClassifyAttribute> list = sqlOperator.selectList(
                "com.qcloud.component.goods.dao.mysql.mapper.ClassifyAttributeMapper.listByClassify", classifyId);
        return list;
    }
}

