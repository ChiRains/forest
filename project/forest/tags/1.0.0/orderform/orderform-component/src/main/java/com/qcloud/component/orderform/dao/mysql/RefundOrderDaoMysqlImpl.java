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
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.component.orderform.dao.RefundOrderDao;
import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.model.query.RefundOrderQuery;
		
@Repository
public class RefundOrderDaoMysqlImpl implements RefundOrderDao {

	@Resource(name = "sqlOperator-orderform")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(RefundOrder refundOrder) {
		return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderMapper.insert", refundOrder) == 1;
	}	
	
	@Override
	public RefundOrder get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(RefundOrder refundOrder){
		return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderMapper.update", refundOrder) > 0;
	}
	
	@Override
	public List<RefundOrder> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, RefundOrder> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<RefundOrder> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<RefundOrder> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderMapper.count4page",
				param);
		Page<RefundOrder> page = new Page<RefundOrder>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<RefundOrder> page(RefundOrderQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);
		param.put("storeId", query.getStoreId());
		param.put("state", query.getState());
		param.put("refundNumber", StringUtil.emptyToNull(query.getRefundNumber()));
		List<RefundOrder> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderMapper.count4query",
				param);
		Page<RefundOrder> page = new Page<RefundOrder>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<RefundOrder> listAll(){	
		List<RefundOrder> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderMapper.listAll");
		return list;
	}

    @Override
    public List<RefundOrder> listBySubOrder(Long subOrderId) {
        List<RefundOrder> list = sqlOperator.selectList(
                "com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderMapper.listBySubOrder", subOrderId);
        return list;
    }

    @Override
    public List<RefundOrder> list4ExpireState(int state, int start, int size) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", size);
        param.put("state", state);
        List<RefundOrder> list = sqlOperator.selectList(
                "com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderMapper.list4ExpireState",
                param);
        return list;
    }
}

