package com.qcloud.component.seckill.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.seckill.dao.MerchandiseSeckillDao;
import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.model.query.MerchandiseSeckillQuery;

@Repository
public class MerchandiseSeckillDaoCacheImpl implements MerchandiseSeckillDao {
	
	@Autowired
	private MerchandiseSeckillDao merchandiseSeckillDaoMysqlImpl;
	
//	@Autowired
//	private MerchandiseSeckillDao merchandiseSeckillDaoRedisImpl;

	@Override
	public boolean add(MerchandiseSeckill merchandiseSeckill) {
		return merchandiseSeckillDaoMysqlImpl.add(merchandiseSeckill);
	}

	@Override
	public MerchandiseSeckill get(Long id) {
	    return merchandiseSeckillDaoMysqlImpl.get(id);
//		return CacheLoader.get(merchandiseSeckillDaoRedisImpl, merchandiseSeckillDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchandiseSeckillDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseSeckill merchandiseSeckill){
		return merchandiseSeckillDaoMysqlImpl.update(merchandiseSeckill);
	}
	
	@Override
	public List<MerchandiseSeckill> list(List<Long> idList) {
		return CacheLoader.list(merchandiseSeckillDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchandiseSeckill> map(Set<Long> idSet){
		return CacheLoader.map(merchandiseSeckillDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MerchandiseSeckill> page(int start, int count){
		return merchandiseSeckillDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchandiseSeckill> page(MerchandiseSeckillQuery query, int start, int count){
		return merchandiseSeckillDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchandiseSeckill> listAll(){
		return merchandiseSeckillDaoMysqlImpl.listAll();
	}

    @Override
    public List<MerchandiseSeckill> listByScreenings(long screeningsId) {
        return merchandiseSeckillDaoMysqlImpl.listByScreenings(screeningsId);
    }

	@Override
	public List<MerchandiseSeckill> listByScreeningsAndQUnifiedMerchandiseId(
			long screeningsId, long qUnifiedMerchandiseId) {
		return merchandiseSeckillDaoMysqlImpl.listByScreeningsAndQUnifiedMerchandiseId(screeningsId, qUnifiedMerchandiseId);
	}
    
    
}

