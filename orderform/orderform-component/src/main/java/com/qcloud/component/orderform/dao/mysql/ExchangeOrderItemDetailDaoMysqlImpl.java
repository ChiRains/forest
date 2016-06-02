package com.qcloud.component.orderform.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.orderform.dao.ExchangeOrderItemDetailDao;
import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;
import com.qcloud.component.orderform.model.query.ExchangeOrderItemDetailQuery;
		
@Repository
public class ExchangeOrderItemDetailDaoMysqlImpl implements ExchangeOrderItemDetailDao {

	@Resource(name = "sqlOperator-orderform")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(ExchangeOrderItemDetail exchangeOrderItemDetail) {
		return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderItemDetailMapper.insert", exchangeOrderItemDetail) == 1;
	}	
	
	@Override
	public ExchangeOrderItemDetail get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderItemDetailMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderItemDetailMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(ExchangeOrderItemDetail exchangeOrderItemDetail){
		return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderItemDetailMapper.update", exchangeOrderItemDetail) > 0;
	}
	
	@Override
	public List<ExchangeOrderItemDetail> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ExchangeOrderItemDetail> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ExchangeOrderItemDetail> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ExchangeOrderItemDetail> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderItemDetailMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderItemDetailMapper.count4page",
				param);
		Page<ExchangeOrderItemDetail> page = new Page<ExchangeOrderItemDetail>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<ExchangeOrderItemDetail> page(ExchangeOrderItemDetailQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ExchangeOrderItemDetail> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderItemDetailMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderItemDetailMapper.count4query",
				param);
		Page<ExchangeOrderItemDetail> page = new Page<ExchangeOrderItemDetail>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<ExchangeOrderItemDetail> listAll(){	
		List<ExchangeOrderItemDetail> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderItemDetailMapper.listAll");
		return list;
	}

    @Override
    public List<ExchangeOrderItemDetail> listByExchange(Long exchangeId) {
        List<ExchangeOrderItemDetail> list = sqlOperator.selectList(
                "com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderItemDetailMapper.listByExchange", exchangeId);
        return list;
    }
}

