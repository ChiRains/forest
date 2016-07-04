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
//import com.qcloud.component.sellercenter.dao.MerchantMerchandiseClassifyDao;
//import com.qcloud.component.sellercenter.model.MerchantMerchandiseClassify;
//import com.qcloud.component.sellercenter.model.query.MerchantMerchandiseClassifyQuery;
//		
//@Repository
//public class MerchantMerchandiseClassifyDaoMysqlImpl implements MerchantMerchandiseClassifyDao {
//
//	@Resource(name = "sqlOperator-sellercenter")
//	private SqlOperator sqlOperator;
//
//	@Override
//	public boolean add(MerchantMerchandiseClassify merchantMerchandiseClassify) {
//		return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMerchandiseClassifyMapper.insert", merchantMerchandiseClassify) == 1;
//	}	
//	
//	@Override
//	public MerchantMerchandiseClassify get(Long id) {
//		return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMerchandiseClassifyMapper.get", id);
//	}	
//	
//	@Override
//	public boolean delete(Long id){
//		return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMerchandiseClassifyMapper.delete", id) > 0;
//	}	
//		
//	@Override
//	public boolean update(MerchantMerchandiseClassify merchantMerchandiseClassify){
//		return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMerchandiseClassifyMapper.update", merchantMerchandiseClassify) > 0;
//	}
//	
//	@Override
//	public List<MerchantMerchandiseClassify> list(List<Long> idList) {
//		throw new NotImplementedException();
//	}
//
//	@Override
//	public Map<Long, MerchantMerchandiseClassify> map(Set<Long> idSet){
//		throw new NotImplementedException();
//	}
//		
//	@Override
//	public Page<MerchantMerchandiseClassify> page(int start, int count){
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("start", start);
//		param.put("count", count);
//
//		List<MerchantMerchandiseClassify> list = sqlOperator.selectList(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMerchandiseClassifyMapper.list4page",
//				param);
//		int total = sqlOperator.selectOne(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMerchandiseClassifyMapper.count4page",
//				param);
//		Page<MerchantMerchandiseClassify> page = new Page<MerchantMerchandiseClassify>();
//		page.setCount(total);
//		page.setData(list);
//		return page;
//	}
//	
//	@Override
//	public Page<MerchantMerchandiseClassify> page(MerchantMerchandiseClassifyQuery query, int start, int count){
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("start", start);
//		param.put("count", count);
//		param.put("merchantId", query.getMerchantId());
//		
//		List<MerchantMerchandiseClassify> list = sqlOperator.selectList(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMerchandiseClassifyMapper.list4query",
//				param);
//		int total = sqlOperator.selectOne(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMerchandiseClassifyMapper.count4query",
//				param);
//		Page<MerchantMerchandiseClassify> page = new Page<MerchantMerchandiseClassify>();
//		page.setCount(total);
//		page.setData(list);
//		return page;
//	}
//	
//	@Override
//	public List<MerchantMerchandiseClassify> listAll(){	
//		List<MerchantMerchandiseClassify> list = sqlOperator.selectList(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMerchandiseClassifyMapper.listAll");
//		return list;
//	}
//
//    @Override
//    public List<MerchantMerchandiseClassify> listByMerchantId(Long merchantId) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("merchantId",merchantId);
//        param.put("start", 0);
//        param.put("count", Integer.MAX_VALUE);
//        List<MerchantMerchandiseClassify> list = sqlOperator.selectList(
//                "com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMerchandiseClassifyMapper.list4query",param);
//        return list;
//    }
//
//    @Override
//    public boolean deleteByMerchantId(Long merchantId) {
//        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MerchantMerchandiseClassifyMapper.deleteByMerchantId", merchantId) > 0;
//    }
//}
//
