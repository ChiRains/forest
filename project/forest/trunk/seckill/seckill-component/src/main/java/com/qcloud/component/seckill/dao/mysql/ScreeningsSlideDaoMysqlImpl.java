package com.qcloud.component.seckill.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.seckill.dao.ScreeningsSlideDao;
import com.qcloud.component.seckill.model.ScreeningsSlide;
import com.qcloud.component.seckill.model.query.ScreeningsSlideQuery;
		
@Repository
public class ScreeningsSlideDaoMysqlImpl implements ScreeningsSlideDao {

	@Resource(name = "sqlOperator-seckill")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(ScreeningsSlide screeningsSlide) {
		return sqlOperator.insert("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsSlideMapper.insert", screeningsSlide) == 1;
	}	
	
	@Override
	public ScreeningsSlide get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsSlideMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsSlideMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(ScreeningsSlide screeningsSlide){
		return sqlOperator.update("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsSlideMapper.update", screeningsSlide) > 0;
	}
	
	@Override
	public List<ScreeningsSlide> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ScreeningsSlide> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ScreeningsSlide> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ScreeningsSlide> list = sqlOperator.selectList(
				"com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsSlideMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsSlideMapper.count4page",
				param);
		Page<ScreeningsSlide> page = new Page<ScreeningsSlide>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<ScreeningsSlide> page(ScreeningsSlideQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ScreeningsSlide> list = sqlOperator.selectList(
				"com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsSlideMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsSlideMapper.count4query",
				param);
		Page<ScreeningsSlide> page = new Page<ScreeningsSlide>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<ScreeningsSlide> listAll(){	
		List<ScreeningsSlide> list = sqlOperator.selectList(
				"com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsSlideMapper.listAll");
		return list;
	}

    @Override
    public List<ScreeningsSlide> listByScreenings(long screeningsId) {
        List<ScreeningsSlide> list = sqlOperator.selectList(
                "com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsSlideMapper.listByScreenings", screeningsId);
        return list;
    }
}

