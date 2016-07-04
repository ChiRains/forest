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
//import com.qcloud.component.sellercenter.dao.MerchantServiceDao;
//import com.qcloud.component.sellercenter.model.MerchantService;
//import com.qcloud.component.sellercenter.model.query.MerchantServiceQuery;
//		
//@Repository
//public class MerchantServiceDaoMysqlImpl implements MerchantServiceDao {
//
//	@Resource(name = "sqlOperator-sellercenter")
//	private SqlOperator sqlOperator;
//
//	@Override
//	public boolean add(MerchantService merchantService) {
//		return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantServiceMapper.insert", merchantService) == 1;
//	}	
//	
//	@Override
//	public MerchantService get(Long id) {
//		return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantServiceMapper.get", id);
//	}	
//	
//	@Override
//	public boolean delete(Long id){
//		return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantServiceMapper.delete", id) > 0;
//	}	
//		
//	@Override
//	public boolean update(MerchantService merchantService){
//		return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantServiceMapper.update", merchantService) > 0;
//	}
//	
//	@Override
//	public List<MerchantService> list(List<Long> idList) {
//		throw new NotImplementedException();
//	}
//
//	@Override
//	public Map<Long, MerchantService> map(Set<Long> idSet){
//		throw new NotImplementedException();
//	}
//		
//	@Override
//	public Page<MerchantService> page(int start, int count){
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("start", start);
//		param.put("count", count);
//
//		List<MerchantService> list = sqlOperator.selectList(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantServiceMapper.list4page",
//				param);
//		int total = sqlOperator.selectOne(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantServiceMapper.count4page",
//				param);
//		Page<MerchantService> page = new Page<MerchantService>();
//		page.setCount(total);
//		page.setData(list);
//		return page;
//	}
//	
//	@Override
//	public Page<MerchantService> page(MerchantServiceQuery query, int start, int count){
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("start", start);
//		param.put("count", count);
//
//		List<MerchantService> list = sqlOperator.selectList(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantServiceMapper.list4query",
//				param);
//		int total = sqlOperator.selectOne(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantServiceMapper.count4query",
//				param);
//		Page<MerchantService> page = new Page<MerchantService>();
//		page.setCount(total);
//		page.setData(list);
//		return page;
//	}
//	
//	@Override
//	public List<MerchantService> listAll(){	
//		List<MerchantService> list = sqlOperator.selectList(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantServiceMapper.listAll");
//		return list;
//	}
//}
//
