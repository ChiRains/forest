//package com.qcloud.component.sellercenter.dao.mysql;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Repository;
//import org.apache.commons.lang.NotImplementedException;
//
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
//import com.qcloud.component.sellercenter.dao.MerchantPayDao;
//import com.qcloud.component.sellercenter.model.MerchantPay;
//import com.qcloud.component.sellercenter.model.query.MerchantPayQuery;
//		
//@Repository
//public class MerchantPayDaoMysqlImpl implements MerchantPayDao {
//
//	@Resource(name = "sqlOperator-sellercenter")
//	private SqlOperator sqlOperator;
//
//	@Override
//	public boolean add(MerchantPay merchantPay) {
//		return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantPayMapper.insert", merchantPay) == 1;
//	}	
//	
//	@Override
//	public MerchantPay get(Long id) {
//		return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantPayMapper.get", id);
//	}	
//	
//	@Override
//	public boolean delete(Long id){
//		return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantPayMapper.delete", id) > 0;
//	}	
//		
//	@Override
//	public boolean update(MerchantPay merchantPay){
//		return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantPayMapper.update", merchantPay) > 0;
//	}
//	
//	@Override
//	public List<MerchantPay> list(List<Long> idList) {
//		throw new NotImplementedException();
//	}
//
//	@Override
//	public Map<Long, MerchantPay> map(Set<Long> idSet){
//		throw new NotImplementedException();
//	}
//		
//	@Override
//	public Page<MerchantPay> page(int start, int count){
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("start", start);
//		param.put("count", count);
//
//		List<MerchantPay> list = sqlOperator.selectList(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantPayMapper.list4page",
//				param);
//		int total = sqlOperator.selectOne(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantPayMapper.count4page",
//				param);
//		Page<MerchantPay> page = new Page<MerchantPay>();
//		page.setCount(total);
//		page.setData(list);
//		return page;
//	}
//	
//	@Override
//	public Page<MerchantPay> page(MerchantPayQuery query, int start, int count){
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("start", start);
//		param.put("count", count);
//
//		List<MerchantPay> list = sqlOperator.selectList(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantPayMapper.list4query",
//				param);
//		int total = sqlOperator.selectOne(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantPayMapper.count4query",
//				param);
//		Page<MerchantPay> page = new Page<MerchantPay>();
//		page.setCount(total);
//		page.setData(list);
//		return page;
//	}
//	
//	@Override
//	public List<MerchantPay> listAll(){	
//		List<MerchantPay> list = sqlOperator.selectList(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantPayMapper.listAll");
//		return list;
//	}
//}
//
