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
//import com.qcloud.component.sellercenter.dao.MerchantMemberDao;
//import com.qcloud.component.sellercenter.model.MerchantMember;
//import com.qcloud.component.sellercenter.model.query.MerchantMemberQuery;
//
//@Repository
//public class MerchantMemberDaoCacheImpl implements MerchantMemberDao {
//	
//	@Autowired
//	private MerchantMemberDao merchantMemberDaoMysqlImpl;
//	
////	@Autowired
////	private MerchantMemberDao merchantMemberDaoRedisImpl;
//
//	@Override
//	public boolean add(MerchantMember merchantMember) {
//		return merchantMemberDaoMysqlImpl.add(merchantMember);
//	}
//
//	@Override
//	public MerchantMember get(Long id) {
//	    return merchantMemberDaoMysqlImpl.get(id);
////	    return CacheLoader.get(merchantMemberDaoRedisImpl, merchantMemberDaoMysqlImpl, id);
//	}
//
//	@Override
//	public boolean delete(Long id){
//		return merchantMemberDaoMysqlImpl.delete(id);
//	}
//	
//	@Override
//	public boolean update(MerchantMember merchantMember){
//		return merchantMemberDaoMysqlImpl.update(merchantMember);
//	}
//	
//	@Override
//	public List<MerchantMember> list(List<Long> idList) {
//        return CacheLoader.list(merchantMemberDaoMysqlImpl, idList);
////		return CacheLoader.list(merchantMemberDaoRedisImpl, merchantMemberDaoMysqlImpl, idList);
//	}
//
//	@Override
//	public Map<Long, MerchantMember> map(Set<Long> idSet){
//        return CacheLoader.map(merchantMemberDaoMysqlImpl, idSet);
////		return CacheLoader.map(merchantMemberDaoRedisImpl, merchantMemberDaoMysqlImpl, idSet);
//	}
//
//	@Override
//	public Page<MerchantMember> page(int start, int count){
//		return merchantMemberDaoMysqlImpl.page(start, count);
//	}
//	
//	@Override
//	public Page<MerchantMember> page(MerchantMemberQuery query, int start, int count){
//		return merchantMemberDaoMysqlImpl.page(query, start, count);
//	}
//	
//	public List<MerchantMember> listAll(){
//		return merchantMemberDaoMysqlImpl.listAll();
//	}
//
//    @Override
//    public List<MerchantMember> listByMerchant(Long merchantId) {
//        return merchantMemberDaoMysqlImpl.listByMerchant(merchantId);
//    }
//
//    @Override
//    public MerchantMember get(Long memberId, Long merchantId) {      
//        return merchantMemberDaoMysqlImpl.get(memberId, merchantId);
//    }
//
//    @Override
//    public List<MerchantMember> listByMember(Long memberId) {       
//        return merchantMemberDaoMysqlImpl.listByMember(memberId);
//    }
//}
//
