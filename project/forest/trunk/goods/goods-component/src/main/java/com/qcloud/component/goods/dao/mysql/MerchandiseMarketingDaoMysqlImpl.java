package com.qcloud.component.goods.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.goods.dao.MerchandiseMarketingDao;
import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.model.query.MerchandiseMarketingQuery;
		
@Repository
public class MerchandiseMarketingDaoMysqlImpl implements MerchandiseMarketingDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MerchandiseMarketing merchandiseMarketing) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMarketingMapper.insert", merchandiseMarketing) == 1;
	}	
	
	@Override
	public MerchandiseMarketing get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMarketingMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMarketingMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MerchandiseMarketing merchandiseMarketing){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMarketingMapper.update", merchandiseMarketing) > 0;
	}
	
	@Override
	public List<MerchandiseMarketing> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseMarketing> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseMarketing> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseMarketing> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMarketingMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMarketingMapper.count4page",
				param);
		Page<MerchandiseMarketing> page = new Page<MerchandiseMarketing>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MerchandiseMarketing> page(MerchandiseMarketingQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseMarketing> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMarketingMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMarketingMapper.count4query",
				param);
		Page<MerchandiseMarketing> page = new Page<MerchandiseMarketing>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MerchandiseMarketing> listAll(){	
		List<MerchandiseMarketing> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMarketingMapper.listAll");
		return list;
	}
}

