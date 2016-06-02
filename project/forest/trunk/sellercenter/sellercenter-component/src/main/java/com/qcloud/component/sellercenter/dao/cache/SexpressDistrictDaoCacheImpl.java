package com.qcloud.component.sellercenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.SexpressDistrictDao;
import com.qcloud.component.sellercenter.model.SexpressDistrict;
import com.qcloud.component.sellercenter.model.query.SexpressDistrictQuery;

@Repository
public class SexpressDistrictDaoCacheImpl implements SexpressDistrictDao {
	
	@Autowired
	private SexpressDistrictDao sexpressDistrictDaoMysqlImpl;
	
	@Autowired
	private SexpressDistrictDao sexpressDistrictDaoRedisImpl;

	@Override
	public boolean add(SexpressDistrict sexpressDistrict) {
		return sexpressDistrictDaoMysqlImpl.add(sexpressDistrict);
	}

	@Override
	public SexpressDistrict get(Long id) {
		//return CacheLoader.get(sexpressDistrictDaoRedisImpl, sexpressDistrictDaoMysqlImpl, id);
	    return sexpressDistrictDaoMysqlImpl.get(id);
	}

	@Override
	public boolean delete(Long id){
		return sexpressDistrictDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(SexpressDistrict sexpressDistrict){
		return sexpressDistrictDaoMysqlImpl.update(sexpressDistrict);
	}
	
	@Override
	public List<SexpressDistrict> list(List<Long> idList) {
		return CacheLoader.list(sexpressDistrictDaoRedisImpl, sexpressDistrictDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, SexpressDistrict> map(Set<Long> idSet){
		return CacheLoader.map(sexpressDistrictDaoRedisImpl, sexpressDistrictDaoMysqlImpl, idSet);
	}

	@Override
	public Page<SexpressDistrict> page(int start, int count){
		return sexpressDistrictDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<SexpressDistrict> page(SexpressDistrictQuery query, int start, int count){
		return sexpressDistrictDaoMysqlImpl.page(query, start, count);
	}
	
	public List<SexpressDistrict> listAll(){
		return sexpressDistrictDaoMysqlImpl.listAll();
	}

    @Override
    public List<SexpressDistrict> listByExpressId(Long id) {
        // TODO Auto-generated method stub
        return sexpressDistrictDaoMysqlImpl.listByExpressId(id);
    }
}

