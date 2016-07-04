//package com.qcloud.component.sellercenter.dao.cache;
//
//import java.util.Map;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.qcloud.pirates.data.CacheLoader;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.component.sellercenter.dao.MerchantMerchandiseClassifyDao;
//import com.qcloud.component.sellercenter.model.MerchantMerchandiseClassify;
//import com.qcloud.component.sellercenter.model.query.MerchantMerchandiseClassifyQuery;
//
//@Repository
//public class MerchantMerchandiseClassifyDaoCacheImpl implements MerchantMerchandiseClassifyDao {
//	
//	@Autowired
//	private MerchantMerchandiseClassifyDao merchantMerchandiseClassifyDaoMysqlImpl;
//	
//	@Autowired
//	private MerchantMerchandiseClassifyDao merchantMerchandiseClassifyDaoRedisImpl;
//
//	@Override
//	public boolean add(MerchantMerchandiseClassify merchantMerchandiseClassify) {
//		return merchantMerchandiseClassifyDaoMysqlImpl.add(merchantMerchandiseClassify);
//	}
//
//	@Override
//	public MerchantMerchandiseClassify get(Long id) {
//		return merchantMerchandiseClassifyDaoMysqlImpl.get(id);
//	}
//
//	@Override
//	public boolean delete(Long id){
//		return merchantMerchandiseClassifyDaoMysqlImpl.delete(id);
//	}
//	
//	@Override
//	public boolean update(MerchantMerchandiseClassify merchantMerchandiseClassify){
//		return merchantMerchandiseClassifyDaoMysqlImpl.update(merchantMerchandiseClassify);
//	}
//	
//	@Override
//	public List<MerchantMerchandiseClassify> list(List<Long> idList) {
//		return CacheLoader.list(merchantMerchandiseClassifyDaoRedisImpl, merchantMerchandiseClassifyDaoMysqlImpl, idList);
//	}
//
//	@Override
//	public Map<Long, MerchantMerchandiseClassify> map(Set<Long> idSet){
//		return CacheLoader.map(merchantMerchandiseClassifyDaoRedisImpl, merchantMerchandiseClassifyDaoMysqlImpl, idSet);
//	}
//
//	@Override
//	public Page<MerchantMerchandiseClassify> page(int start, int count){
//		return merchantMerchandiseClassifyDaoMysqlImpl.page(start, count);
//	}
//	
//	@Override
//	public Page<MerchantMerchandiseClassify> page(MerchantMerchandiseClassifyQuery query, int start, int count){
//		return merchantMerchandiseClassifyDaoMysqlImpl.page(query, start, count);
//	}
//	
//	public List<MerchantMerchandiseClassify> listAll(){
//		return merchantMerchandiseClassifyDaoMysqlImpl.listAll();
//	}
//
//    @Override
//    public List<MerchantMerchandiseClassify> listByMerchantId(Long merchantId) {
//        
//        return merchantMerchandiseClassifyDaoMysqlImpl.listByMerchantId(merchantId);
//    }
//
//    @Override
//    public boolean deleteByMerchantId(Long merchantId) {
//        return merchantMerchandiseClassifyDaoMysqlImpl.deleteByMerchantId(merchantId);
//    }
//}
//
