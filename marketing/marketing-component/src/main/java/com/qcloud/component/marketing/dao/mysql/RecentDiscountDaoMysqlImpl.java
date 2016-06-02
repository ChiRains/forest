package com.qcloud.component.marketing.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.marketing.dao.RecentDiscountDao;
import com.qcloud.component.marketing.model.RecentDiscount;
import com.qcloud.component.marketing.model.query.RecentDiscountQuery;
		
@Repository
public class RecentDiscountDaoMysqlImpl implements RecentDiscountDao {

	@Resource(name = "sqlOperator-marketing")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(RecentDiscount recentDiscount) {
		return sqlOperator.insert("com.qcloud.component.marketing.dao.mysql.mapper.RecentDiscountMapper.insert", recentDiscount) == 1;
	}	
	
	@Override
	public RecentDiscount get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.RecentDiscountMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.marketing.dao.mysql.mapper.RecentDiscountMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(RecentDiscount recentDiscount){
		return sqlOperator.update("com.qcloud.component.marketing.dao.mysql.mapper.RecentDiscountMapper.update", recentDiscount) > 0;
	}
	
	@Override
	public List<RecentDiscount> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, RecentDiscount> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<RecentDiscount> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<RecentDiscount> list = sqlOperator.selectList(
				"com.qcloud.component.marketing.dao.mysql.mapper.RecentDiscountMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.marketing.dao.mysql.mapper.RecentDiscountMapper.count4page",
				param);
		Page<RecentDiscount> page = new Page<RecentDiscount>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<RecentDiscount> page(RecentDiscountQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);
		param.put("name", query.getName());
		param.put("merchantId", query.getMerchantId());
		List<RecentDiscount> list = sqlOperator.selectList(
				"com.qcloud.component.marketing.dao.mysql.mapper.RecentDiscountMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.marketing.dao.mysql.mapper.RecentDiscountMapper.count4query",
				param);
		Page<RecentDiscount> page = new Page<RecentDiscount>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<RecentDiscount> listAll(){	
		List<RecentDiscount> list = sqlOperator.selectList(
				"com.qcloud.component.marketing.dao.mysql.mapper.RecentDiscountMapper.listAll");
		return list;
	}

    @Override
    public List<RecentDiscount> list(RecentDiscountQuery query, int start, int size) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", size);
        param.put("name", query.getName());
        param.put("merchantId", query.getMerchantId());
        param.put("date", query.getDate());
        List<RecentDiscount> list = sqlOperator.selectList(
                "com.qcloud.component.marketing.dao.mysql.mapper.RecentDiscountMapper.list",param);
        return list;
    }

    @Override
    public int count(RecentDiscountQuery query) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", query.getName());
        param.put("merchantId", query.getMerchantId());
        int total = sqlOperator.selectOne(
                "com.qcloud.component.marketing.dao.mysql.mapper.RecentDiscountMapper.count",
                param);
        return total;
    }
}

