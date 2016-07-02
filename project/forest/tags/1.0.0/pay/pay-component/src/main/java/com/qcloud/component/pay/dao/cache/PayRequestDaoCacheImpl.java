package com.qcloud.component.pay.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.pay.dao.PayRequestDao;
import com.qcloud.component.pay.model.PayRequest;
import com.qcloud.component.pay.model.query.PayRequestQuery;

@Repository
public class PayRequestDaoCacheImpl implements PayRequestDao {
	
	@Autowired
	private PayRequestDao payRequestDaoMysqlImpl;
	
//	@Autowired
//	private PayRequestDao payRequestDaoRedisImpl;

	@Override
	public boolean add(PayRequest payRequest) {
		return payRequestDaoMysqlImpl.add(payRequest);
	}

	@Override
	public PayRequest get(Long id) {
	    return payRequestDaoMysqlImpl.get(id);
//		return CacheLoader.get(payRequestDaoRedisImpl, payRequestDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return payRequestDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(PayRequest payRequest){
		return payRequestDaoMysqlImpl.update(payRequest);
	}
	
	@Override
	public List<PayRequest> list(List<Long> idList) {
		return CacheLoader.list(payRequestDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, PayRequest> map(Set<Long> idSet){
		return CacheLoader.map(payRequestDaoMysqlImpl, idSet);
	}

	@Override
	public Page<PayRequest> page(int start, int count){
		return payRequestDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<PayRequest> page(PayRequestQuery query, int start, int count){
		return payRequestDaoMysqlImpl.page(query, start, count);
	}
	
	public List<PayRequest> listAll(){
		return payRequestDaoMysqlImpl.listAll();
	}

    @Override
    public PayRequest getByObj(String module, Long objId, String type, String client) {
        return payRequestDaoMysqlImpl.getByObj(module, objId, type, client);
    }

    @Override
    public PayRequest getByAttach(String module, String attach, String type, String client) {
        return payRequestDaoMysqlImpl.getByAttach(module, attach, type, client);
    }
}

