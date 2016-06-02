package com.qcloud.component.sellercenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.sellercenter.dao.SexpressDistrictDao;
import com.qcloud.component.sellercenter.model.SexpressDistrict;
import com.qcloud.component.sellercenter.model.query.SexpressDistrictQuery;
		
@Repository
public class SexpressDistrictDaoMysqlImpl implements SexpressDistrictDao {

	@Resource(name = "sqlOperator-sellercenter")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(SexpressDistrict sexpressDistrict) {
		return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressDistrictMapper.insert", sexpressDistrict) == 1;
	}	
	
	@Override
	public SexpressDistrict get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressDistrictMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressDistrictMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(SexpressDistrict sexpressDistrict){
		return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressDistrictMapper.update", sexpressDistrict) > 0;
	}
	
	@Override
	public List<SexpressDistrict> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, SexpressDistrict> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<SexpressDistrict> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<SexpressDistrict> list = sqlOperator.selectList(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressDistrictMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressDistrictMapper.count4page",
				param);
		Page<SexpressDistrict> page = new Page<SexpressDistrict>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<SexpressDistrict> page(SexpressDistrictQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<SexpressDistrict> list = sqlOperator.selectList(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressDistrictMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressDistrictMapper.count4query",
				param);
		Page<SexpressDistrict> page = new Page<SexpressDistrict>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<SexpressDistrict> listAll(){	
		List<SexpressDistrict> list = sqlOperator.selectList(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressDistrictMapper.listAll");
		return list;
	}

    @Override
    public List<SexpressDistrict> listByExpressId(Long expressId) {
        List<SexpressDistrict> list = sqlOperator.selectList(
                "com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressDistrictMapper.listByExpressId",expressId);
        return list;
    }
}

