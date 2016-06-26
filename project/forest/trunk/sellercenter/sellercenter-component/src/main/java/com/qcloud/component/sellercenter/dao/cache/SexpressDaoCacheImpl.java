package com.qcloud.component.sellercenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.SexpressDao;
import com.qcloud.component.sellercenter.model.Sexpress;
import com.qcloud.component.sellercenter.model.query.SexpressQuery;

@Repository
public class SexpressDaoCacheImpl implements SexpressDao {
	
	@Autowired
	private SexpressDao sexpressDaoMysqlImpl;
	
	@Autowired
	private SexpressDao sexpressDaoRedisImpl;

	@Override
	public boolean add(Sexpress sexpress) {
		return sexpressDaoMysqlImpl.add(sexpress);
	}

	@Override
	public Sexpress get(Long id) {
		//return CacheLoader.get(sexpressDaoRedisImpl, sexpressDaoMysqlImpl, id);
	    return sexpressDaoMysqlImpl.get(id);
	}

	@Override
	public boolean delete(Long id){
		return sexpressDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(Sexpress sexpress){
		return sexpressDaoMysqlImpl.update(sexpress);
	}
	
	@Override
	public List<Sexpress> list(List<Long> idList) {
		return CacheLoader.list(sexpressDaoRedisImpl, sexpressDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, Sexpress> map(Set<Long> idSet){
		return CacheLoader.map(sexpressDaoRedisImpl, sexpressDaoMysqlImpl, idSet);
	}

	@Override
	public Page<Sexpress> page(int start, int count){
		return sexpressDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<Sexpress> page(SexpressQuery query, int start, int count){
		return sexpressDaoMysqlImpl.page(query, start, count);
	}
	
	public List<Sexpress> listAll(){
		return sexpressDaoMysqlImpl.listAll();
	}

    @Override
    public List<Sexpress> listByMerchant(Long merchantId) {

        return sexpressDaoMysqlImpl.listByMerchant(merchantId);
    }

    @Override
    public Sexpress getByCode(String expressCode, long merchantId) {

        return sexpressDaoMysqlImpl.getByCode(expressCode, merchantId);
    }
}

