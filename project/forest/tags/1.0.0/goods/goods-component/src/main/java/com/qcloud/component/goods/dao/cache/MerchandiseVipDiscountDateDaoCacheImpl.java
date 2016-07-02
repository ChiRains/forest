package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseVipDiscountDateDao;
import com.qcloud.component.goods.model.MerchandiseVipDiscountDate;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountDateQuery;

@Repository
public class MerchandiseVipDiscountDateDaoCacheImpl implements MerchandiseVipDiscountDateDao {
	
	@Autowired
	private MerchandiseVipDiscountDateDao merchandiseVipDiscountDateDaoMysqlImpl;
	
//	@Autowired
//	private MerchandiseVipDiscountDateDao merchandiseVipDiscountDateDaoRedisImpl;

	@Override
	public boolean add(MerchandiseVipDiscountDate merchandiseVipDiscountDate) {
		return merchandiseVipDiscountDateDaoMysqlImpl.add(merchandiseVipDiscountDate);
	}

	@Override
	public MerchandiseVipDiscountDate get(Long id) {
	    return merchandiseVipDiscountDateDaoMysqlImpl.get(id);
//		return CacheLoader.get(merchandiseVipDiscountDateDaoRedisImpl, merchandiseVipDiscountDateDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchandiseVipDiscountDateDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseVipDiscountDate merchandiseVipDiscountDate){
		return merchandiseVipDiscountDateDaoMysqlImpl.update(merchandiseVipDiscountDate);
	}
	
	@Override
	public List<MerchandiseVipDiscountDate> list(List<Long> idList) {
		return CacheLoader.list(merchandiseVipDiscountDateDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchandiseVipDiscountDate> map(Set<Long> idSet){
		return CacheLoader.map(merchandiseVipDiscountDateDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MerchandiseVipDiscountDate> page(int start, int count){
		return merchandiseVipDiscountDateDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchandiseVipDiscountDate> page(MerchandiseVipDiscountDateQuery query, int start, int count){
		return merchandiseVipDiscountDateDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchandiseVipDiscountDate> listAll(){
		return merchandiseVipDiscountDateDaoMysqlImpl.listAll();
	}

    @Override
    public MerchandiseVipDiscountDate getByUser(Long userId, int year, int month, int day) {
        return merchandiseVipDiscountDateDaoMysqlImpl.getByUser(userId, year, month, day);
    }

    @Override
    public MerchandiseVipDiscountDate getLastByUser(Long userId) {
        return merchandiseVipDiscountDateDaoMysqlImpl.getLastByUser(userId);
    }
}

