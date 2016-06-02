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
import com.qcloud.component.sellercenter.dao.MerchantConsumptionDetailDao;
import com.qcloud.component.sellercenter.model.MerchantConsumptionDetail;
import com.qcloud.component.sellercenter.model.query.MerchantConsumptionDetailQuery;
		
@Repository
public class MerchantConsumptionDetailDaoMysqlImpl implements MerchantConsumptionDetailDao {

	@Resource(name = "sqlOperator-sellercenter")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MerchantConsumptionDetail merchantConsumptionDetail) {
		return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantConsumptionDetailMapper.insert", merchantConsumptionDetail) == 1;
	}	
	
	@Override
	public MerchantConsumptionDetail get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantConsumptionDetailMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantConsumptionDetailMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MerchantConsumptionDetail merchantConsumptionDetail){
		return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantConsumptionDetailMapper.update", merchantConsumptionDetail) > 0;
	}
	
	@Override
	public List<MerchantConsumptionDetail> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchantConsumptionDetail> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchantConsumptionDetail> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchantConsumptionDetail> list = sqlOperator.selectList(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantConsumptionDetailMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantConsumptionDetailMapper.count4page",
				param);
		Page<MerchantConsumptionDetail> page = new Page<MerchantConsumptionDetail>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MerchantConsumptionDetail> page(MerchantConsumptionDetailQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);
		param.put("merchantId", query.getMerchantId());
		List<MerchantConsumptionDetail> list = sqlOperator.selectList(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantConsumptionDetailMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantConsumptionDetailMapper.count4query",
				param);
		Page<MerchantConsumptionDetail> page = new Page<MerchantConsumptionDetail>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MerchantConsumptionDetail> listAll(){	
		List<MerchantConsumptionDetail> list = sqlOperator.selectList(
				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantConsumptionDetailMapper.listAll");
		return list;
	}
}

