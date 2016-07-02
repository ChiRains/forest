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
import com.qcloud.component.orderform.dao.MembershipCardOrderCouponDao;
import com.qcloud.component.orderform.model.MembershipCardOrderCoupon;
import com.qcloud.component.orderform.model.query.MembershipCardOrderCouponQuery;
		
@Repository
public class MembershipCardOrderCouponDaoMysqlImpl implements MembershipCardOrderCouponDao {

	@Resource(name = "sqlOperator-orderform")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MembershipCardOrderCoupon membershipCardOrderCoupon) {
		return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderCouponMapper.insert", membershipCardOrderCoupon) == 1;
	}	
	
	@Override
	public MembershipCardOrderCoupon get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderCouponMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderCouponMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MembershipCardOrderCoupon membershipCardOrderCoupon){
		return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderCouponMapper.update", membershipCardOrderCoupon) > 0;
	}
	
	@Override
	public List<MembershipCardOrderCoupon> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MembershipCardOrderCoupon> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MembershipCardOrderCoupon> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MembershipCardOrderCoupon> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderCouponMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderCouponMapper.count4page",
				param);
		Page<MembershipCardOrderCoupon> page = new Page<MembershipCardOrderCoupon>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MembershipCardOrderCoupon> page(MembershipCardOrderCouponQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MembershipCardOrderCoupon> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderCouponMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderCouponMapper.count4query",
				param);
		Page<MembershipCardOrderCoupon> page = new Page<MembershipCardOrderCoupon>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MembershipCardOrderCoupon> listAll(){	
		List<MembershipCardOrderCoupon> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderCouponMapper.listAll");
		return list;
	}
}

