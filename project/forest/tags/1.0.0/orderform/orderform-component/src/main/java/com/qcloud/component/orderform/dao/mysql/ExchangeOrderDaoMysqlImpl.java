package com.qcloud.component.orderform.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.ExchangeOrderDao;
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.query.ExchangeOrderQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.StringUtil;
		
@Repository
public class ExchangeOrderDaoMysqlImpl implements ExchangeOrderDao {

	@Resource(name = "sqlOperator-orderform")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(ExchangeOrder exchangeOrder) {
		return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderMapper.insert", exchangeOrder) == 1;
	}	
	
	@Override
	public ExchangeOrder get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(ExchangeOrder exchangeOrder){
		return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderMapper.update", exchangeOrder) > 0;
	}
	
	@Override
	public List<ExchangeOrder> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ExchangeOrder> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ExchangeOrder> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ExchangeOrder> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderMapper.count4page",
				param);
		Page<ExchangeOrder> page = new Page<ExchangeOrder>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<ExchangeOrder> page(ExchangeOrderQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);
		param.put("merchantId", query.getMerchantId());
        param.put("storeId", query.getStoreId());
        param.put("state", query.getState());
        param.put("exchangeNumber", StringUtil.emptyToNull(query.getExchangeNumber()));
		List<ExchangeOrder> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderMapper.count4query",
				param);
		Page<ExchangeOrder> page = new Page<ExchangeOrder>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<ExchangeOrder> listAll(){	
		List<ExchangeOrder> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderMapper.listAll");
		return list;
	}

    @Override
    public List<ExchangeOrder> listBySubOrder(Long subOrderId) {
        List<ExchangeOrder> list = sqlOperator.selectList(
                "com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderMapper.listBySubOrder", subOrderId);
        return list;
    }

    @Override
    public List<ExchangeOrder> list4ExpireState(int state, int start, int size) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", size);
        param.put("state", state);
        param.put("now", new Date());        
        List<ExchangeOrder> list = sqlOperator.selectList(
                "com.qcloud.component.orderform.dao.mysql.mapper.ExchangeOrderMapper.list4ExpireState",
                param);    
        return list;
    }
}

