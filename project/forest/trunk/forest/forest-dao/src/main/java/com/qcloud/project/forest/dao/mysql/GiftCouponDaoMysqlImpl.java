package com.qcloud.project.forest.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.project.forest.dao.GiftCouponDao;
import com.qcloud.project.forest.model.GiftCoupon;
import com.qcloud.project.forest.model.query.GiftCouponQuery;
		
@Repository
public class GiftCouponDaoMysqlImpl implements GiftCouponDao {

	@Resource(name = "sqlOperator-forest")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(GiftCoupon giftCoupon) {
		return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponMapper.insert", giftCoupon) == 1;
	}	
	
	@Override
	public GiftCoupon get(Long id) {
		return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(GiftCoupon giftCoupon){
		return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponMapper.update", giftCoupon) > 0;
	}
	
	@Override
	public List<GiftCoupon> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, GiftCoupon> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<GiftCoupon> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<GiftCoupon> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.GiftCouponMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.project.forest.dao.mysql.mapper.GiftCouponMapper.count4page",
				param);
		Page<GiftCoupon> page = new Page<GiftCoupon>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<GiftCoupon> page(GiftCouponQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<GiftCoupon> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.GiftCouponMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.project.forest.dao.mysql.mapper.GiftCouponMapper.count4query",
				param);
		Page<GiftCoupon> page = new Page<GiftCoupon>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<GiftCoupon> listAll(){	
		List<GiftCoupon> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.GiftCouponMapper.listAll");
		return list;
	}
}

