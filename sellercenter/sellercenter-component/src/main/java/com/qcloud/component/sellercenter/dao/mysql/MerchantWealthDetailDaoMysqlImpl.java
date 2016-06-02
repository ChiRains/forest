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
import com.qcloud.component.sellercenter.dao.MerchantWealthDetailDao;
import com.qcloud.component.sellercenter.model.MerchantWealthDetail;
import com.qcloud.component.sellercenter.model.query.MerchantWealthDetailQuery;
		
@Repository
public class MerchantWealthDetailDaoMysqlImpl implements MerchantWealthDetailDao {

	@Resource(name = "sqlOperator-sellercenter")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MerchantWealthDetail merchantWealthDetail) {
		return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthDetailMapper.insert", merchantWealthDetail) == 1;
	}	
	
	@Override
	public MerchantWealthDetail get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthDetailMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthDetailMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MerchantWealthDetail merchantWealthDetail){
		return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthDetailMapper.update", merchantWealthDetail) > 0;
	}
	
	@Override
	public List<MerchantWealthDetail> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchantWealthDetail> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchantWealthDetail> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchantWealthDetail> list = sqlOperator.selectList(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthDetailMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthDetailMapper.count4page",
				param);
		Page<MerchantWealthDetail> page = new Page<MerchantWealthDetail>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MerchantWealthDetail> page(MerchantWealthDetailQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchantWealthDetail> list = sqlOperator.selectList(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthDetailMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthDetailMapper.count4query",
				param);
		Page<MerchantWealthDetail> page = new Page<MerchantWealthDetail>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MerchantWealthDetail> listAll(){	
		List<MerchantWealthDetail> list = sqlOperator.selectList(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantWealthDetailMapper.listAll");
		return list;
	}
}

