package com.qcloud.component.seckill.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.seckill.dao.ScreeningsSlideDao;
import com.qcloud.component.seckill.model.ScreeningsSlide;
import com.qcloud.component.seckill.model.query.ScreeningsSlideQuery;

@Repository
public class ScreeningsSlideDaoCacheImpl implements ScreeningsSlideDao {
	
	@Autowired
	private ScreeningsSlideDao screeningsSlideDaoMysqlImpl;
	
//	@Autowired
//	private ScreeningsSlideDao screeningsSlideDaoRedisImpl;

	@Override
	public boolean add(ScreeningsSlide screeningsSlide) {
		return screeningsSlideDaoMysqlImpl.add(screeningsSlide);
	}

	@Override
	public ScreeningsSlide get(Long id) {
	    return screeningsSlideDaoMysqlImpl.get(id);
//		return CacheLoader.get(screeningsSlideDaoRedisImpl, screeningsSlideDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return screeningsSlideDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(ScreeningsSlide screeningsSlide){
		return screeningsSlideDaoMysqlImpl.update(screeningsSlide);
	}
	
	@Override
	public List<ScreeningsSlide> list(List<Long> idList) {
		return CacheLoader.list(screeningsSlideDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, ScreeningsSlide> map(Set<Long> idSet){
		return CacheLoader.map(screeningsSlideDaoMysqlImpl, idSet);
	}

	@Override
	public Page<ScreeningsSlide> page(int start, int count){
		return screeningsSlideDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<ScreeningsSlide> page(ScreeningsSlideQuery query, int start, int count){
		return screeningsSlideDaoMysqlImpl.page(query, start, count);
	}
	
	public List<ScreeningsSlide> listAll(){
		return screeningsSlideDaoMysqlImpl.listAll();
	}

    @Override
    public List<ScreeningsSlide> listByScreenings(long screeningsId) {
        return screeningsSlideDaoMysqlImpl.listByScreenings(screeningsId);
    }
}

