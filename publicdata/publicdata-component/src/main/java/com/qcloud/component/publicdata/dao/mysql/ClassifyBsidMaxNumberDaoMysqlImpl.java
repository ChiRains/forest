package com.qcloud.component.publicdata.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.publicdata.dao.ClassifyBsidMaxNumberDao;
import com.qcloud.component.publicdata.model.ClassifyBsidMaxNumber;
import com.qcloud.component.publicdata.model.query.ClassifyBsidMaxNumberQuery;
		
@Repository
public class ClassifyBsidMaxNumberDaoMysqlImpl implements ClassifyBsidMaxNumberDao {

	@Resource(name = "sqlOperator-publicdata")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(ClassifyBsidMaxNumber classifyBsidMaxNumber) {
		return sqlOperator.insert("com.qcloud.component.publicdata.dao.mysql.mapper.ClassifyBsidMaxNumberMapper.insert", classifyBsidMaxNumber) == 1;
	}	
	
	@Override
	public ClassifyBsidMaxNumber get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.publicdata.dao.mysql.mapper.ClassifyBsidMaxNumberMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.publicdata.dao.mysql.mapper.ClassifyBsidMaxNumberMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(ClassifyBsidMaxNumber classifyBsidMaxNumber){
		return sqlOperator.update("com.qcloud.component.publicdata.dao.mysql.mapper.ClassifyBsidMaxNumberMapper.update", classifyBsidMaxNumber) > 0;
	}
	
	@Override
	public List<ClassifyBsidMaxNumber> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ClassifyBsidMaxNumber> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ClassifyBsidMaxNumber> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ClassifyBsidMaxNumber> list = sqlOperator.selectList(
				"com.qcloud.component.publicdata.dao.mysql.mapper.ClassifyBsidMaxNumberMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.publicdata.dao.mysql.mapper.ClassifyBsidMaxNumberMapper.count4page",
				param);
		Page<ClassifyBsidMaxNumber> page = new Page<ClassifyBsidMaxNumber>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<ClassifyBsidMaxNumber> page(ClassifyBsidMaxNumberQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ClassifyBsidMaxNumber> list = sqlOperator.selectList(
				"com.qcloud.component.publicdata.dao.mysql.mapper.ClassifyBsidMaxNumberMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.publicdata.dao.mysql.mapper.ClassifyBsidMaxNumberMapper.count4query",
				param);
		Page<ClassifyBsidMaxNumber> page = new Page<ClassifyBsidMaxNumber>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<ClassifyBsidMaxNumber> listAll(){	
		List<ClassifyBsidMaxNumber> list = sqlOperator.selectList(
				"com.qcloud.component.publicdata.dao.mysql.mapper.ClassifyBsidMaxNumberMapper.listAll");
		return list;
	}   

    @Override
    public ClassifyBsidMaxNumber getByParentClassify(long parentClassifyId, long type) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parentClassifyId", parentClassifyId);
        param.put("type", type);
        return sqlOperator.selectOne("com.qcloud.component.publicdata.dao.mysql.mapper.ClassifyBsidMaxNumberMapper.getByParentClassify", param);
    }

    @Override
    public void lockNextBsid(ClassifyBsidMaxNumber classifyBsidMaxNumber) {
        sqlOperator.insert("com.qcloud.component.publicdata.dao.mysql.mapper.ClassifyBsidMaxNumberMapper.lockNextBsid", classifyBsidMaxNumber);
    }
}

