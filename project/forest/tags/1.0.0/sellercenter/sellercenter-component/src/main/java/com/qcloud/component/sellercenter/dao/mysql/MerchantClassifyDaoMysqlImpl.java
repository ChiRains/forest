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
import com.qcloud.component.sellercenter.dao.MerchantClassifyDao;
import com.qcloud.component.sellercenter.model.MerchantClassify;
import com.qcloud.component.sellercenter.model.query.MerchantClassifyQuery;
		
@Repository
public class MerchantClassifyDaoMysqlImpl implements MerchantClassifyDao {

	@Resource(name = "sqlOperator-sellercenter")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MerchantClassify merchantClassify) {
		return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantClassifyMapper.insert", merchantClassify) == 1;
	}	
	
	@Override
	public MerchantClassify get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantClassifyMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantClassifyMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MerchantClassify merchantClassify){
		return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantClassifyMapper.update", merchantClassify) > 0;
	}
	
	@Override
	public List<MerchantClassify> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchantClassify> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchantClassify> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchantClassify> list = sqlOperator.selectList(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantClassifyMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantClassifyMapper.count4page",
				param);
		Page<MerchantClassify> page = new Page<MerchantClassify>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MerchantClassify> page(MerchantClassifyQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchantClassify> list = sqlOperator.selectList(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantClassifyMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantClassifyMapper.count4query",
				param);
		Page<MerchantClassify> page = new Page<MerchantClassify>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MerchantClassify> listAll(){	
		List<MerchantClassify> list = sqlOperator.selectList(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantClassifyMapper.listAll");
		return list;
	}

    @Override
    public List<MerchantClassify> listByMerchant(Long merchantId) {
        List<MerchantClassify> list = sqlOperator.selectList(
                "com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantClassifyMapper.listByMerchant", merchantId);
        return list;
    }

    @Override
    public MerchantClassify get(Long classifyId, Long merchantId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("classifyId", classifyId);
        param.put("merchantId", merchantId); 
        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantClassifyMapper.getByClassifyAndMerchant", param);
   }
}

