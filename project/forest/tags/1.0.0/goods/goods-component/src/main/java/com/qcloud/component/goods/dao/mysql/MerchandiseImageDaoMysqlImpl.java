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
import com.qcloud.component.goods.dao.MerchandiseImageDao;
import com.qcloud.component.goods.model.MerchandiseImage;
import com.qcloud.component.goods.model.query.MerchandiseImageQuery;
		
@Repository
public class MerchandiseImageDaoMysqlImpl implements MerchandiseImageDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MerchandiseImage merchandiseImage) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseImageMapper.insert", merchandiseImage) == 1;
	}	
	
	@Override
	public MerchandiseImage get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseImageMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseImageMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MerchandiseImage merchandiseImage){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseImageMapper.update", merchandiseImage) > 0;
	}
	
	@Override
	public List<MerchandiseImage> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseImage> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseImage> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseImage> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseImageMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseImageMapper.count4page",
				param);
		Page<MerchandiseImage> page = new Page<MerchandiseImage>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MerchandiseImage> page(MerchandiseImageQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseImage> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseImageMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseImageMapper.count4query",
				param);
		Page<MerchandiseImage> page = new Page<MerchandiseImage>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MerchandiseImage> listAll(){	
		List<MerchandiseImage> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseImageMapper.listAll");
		return list;
	}

    @Override
    public List<MerchandiseImage> listByMerchandise(Long merchandiseId) {
        List<MerchandiseImage> list = sqlOperator.selectList(
                "com.qcloud.component.goods.dao.mysql.mapper.MerchandiseImageMapper.listByMerchandise", merchandiseId);
        return list;
    }

    @Override
    public List<MerchandiseImage> listByMerchandiseAndAttribute(Long merchandiseId, Long attributeId, String value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchandiseId", merchandiseId);
        param.put("attributeId", attributeId);
        param.put("value", value);        
        
        List<MerchandiseImage> list = sqlOperator.selectList(
                "com.qcloud.component.goods.dao.mysql.mapper.MerchandiseImageMapper.listByMerchandiseAndAttribute", param);
        return list;
    }
}

